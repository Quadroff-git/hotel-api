package org.pileka.hotel_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pileka.hotel_api.domain.Hotel;
import org.pileka.hotel_api.dto.*;
import org.pileka.hotel_api.exception.EntityDoesntExistException;
import org.pileka.hotel_api.exception.InvalidInputException;
import org.pileka.hotel_api.mapper.HotelMapper;
import org.pileka.hotel_api.repository.HotelRepository;
import org.pileka.hotel_api.service.impl.HotelServiceImpl;
import org.pileka.hotel_api.specification.HotelSpecificationUtil;
import org.pileka.hotel_api.test_util.HotelTestUtil;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {

    @Mock
    private HotelRepository repository;

    @Mock
    private HotelMapper mapper;

    @InjectMocks
    private HotelServiceImpl hotelService;

    private Hotel sampleHotel;
    private CreateHotelDTO sampleCreateDTO;
    private ResponseHotelDTO sampleResponseDTO;
    private ShortResponseHotelDTO sampleShortResponseDTO;
    private SearchHotelDTO sampleSearchDTO;
    private List<String> sampleAmenities;
    private List<HistogramItemDTO> sampleHistogramData;

    @BeforeEach
    void setUp() {
        sampleHotel = HotelTestUtil.getHotel();
        sampleCreateDTO = HotelTestUtil.getCreateDto();
        sampleResponseDTO = HotelTestUtil.getResponseHotelDTO();
        sampleShortResponseDTO = HotelTestUtil.getShortResponseHotelDTO();
        sampleSearchDTO = HotelTestUtil.getSearchHotelDTO();
        sampleAmenities = HotelTestUtil.getAmenities();
        sampleHistogramData = Arrays.asList(
                new HistogramItemDTO("Hilton", 10L),
                new HistogramItemDTO("Marriott", 5L)
        );
    }

    // create()
    @Test
    void create_ShouldReturnShortResponseDTO_WhenValidInput() {
        when(mapper.toModel(sampleCreateDTO)).thenReturn(sampleHotel);
        when(repository.save(sampleHotel)).thenReturn(sampleHotel);
        when(mapper.toShortDto(sampleHotel)).thenReturn(sampleShortResponseDTO);

        ShortResponseHotelDTO result = hotelService.create(sampleCreateDTO);

        assertThat(result).isNotNull();
        HotelTestUtil.assertDtoEqualsModel(result, sampleHotel);

        verify(mapper).toModel(sampleCreateDTO);
        verify(repository).save(sampleHotel);
        verify(mapper).toShortDto(sampleHotel);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void create_ShouldPropagateException_WhenMapperThrows() {
        when(mapper.toModel(sampleCreateDTO)).thenThrow(new RuntimeException("Mapping error"));

        assertThatThrownBy(() -> hotelService.create(sampleCreateDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Mapping error");

        verify(repository, never()).save(any());
    }

    // get(long id)
    @Test
    void get_ShouldReturnResponseDTO_WhenHotelExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(sampleHotel));
        when(mapper.toDto(sampleHotel)).thenReturn(sampleResponseDTO);

        ResponseHotelDTO result = hotelService.get(1L);

        assertThat(result).isNotNull();
        HotelTestUtil.assertDtoEqualsModel(result, sampleHotel);

        verify(repository).findById(1L);
        verify(mapper).toDto(sampleHotel);
    }

    @Test
    void get_ShouldThrowEntityDoesntExistException_WhenHotelNotFound() {
        long nonExistentId = 999L;
        when(repository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.get(nonExistentId))
                .isInstanceOf(EntityDoesntExistException.class)
                .hasMessageContaining("Hotel with id " + nonExistentId + "doesn't exist");

        verify(repository).findById(nonExistentId);
        verify(mapper, never()).toDto(any());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L})
    void get_ShouldThrowEntityDoesntExistException_WhenInvalidId(Long invalidId) {
        when(repository.findById(invalidId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.get(invalidId))
                .isInstanceOf(EntityDoesntExistException.class);

        verify(repository).findById(invalidId);
    }

    // get()
    @Test
    void get_ShouldReturnListOfShortResponseDTOs_WhenHotelsExist() {
        List<Hotel> hotels = Arrays.asList(sampleHotel, HotelTestUtil.getHotel());
        Hotel secondHotel = HotelTestUtil.getHotel();
        secondHotel.setId(2L);
        secondHotel.setName("Marriott Minsk");

        when(repository.findAll()).thenReturn(Arrays.asList(sampleHotel, secondHotel));
        when(mapper.toShortDto(sampleHotel)).thenReturn(sampleShortResponseDTO);

        ShortResponseHotelDTO secondShortDTO = ShortResponseHotelDTO.builder()
                .id(2L)
                .name("Marriott Minsk")
                .build();
        when(mapper.toShortDto(secondHotel)).thenReturn(secondShortDTO);

        List<ShortResponseHotelDTO> result = hotelService.get();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);

        verify(repository).findAll();
        verify(mapper, times(2)).toShortDto(any(Hotel.class));
    }

    @Test
    void get_ShouldReturnEmptyList_WhenNoHotelsExist() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<ShortResponseHotelDTO> result = hotelService.get();

        assertThat(result).isEmpty();
        verify(repository).findAll();
        verify(mapper, never()).toShortDto(any());
    }

    // get(SearchHotelDTO searchDto)
    @Test
    void get_WithSearchDTO_ShouldReturnFilteredResults() {
        // Given
        Specification<Hotel> mockSpecification = mock(Specification.class);
        List<Hotel> filteredHotels = Collections.singletonList(sampleHotel);

        try (var mockedStatic = mockStatic(HotelSpecificationUtil.class)) {
            mockedStatic.when(() -> HotelSpecificationUtil.fromSearchDTO(sampleSearchDTO))
                    .thenReturn(mockSpecification);
            when(repository.findAll(mockSpecification)).thenReturn(filteredHotels);
            when(mapper.toShortDto(sampleHotel)).thenReturn(sampleShortResponseDTO);

            List<ShortResponseHotelDTO> result = hotelService.get(sampleSearchDTO);

            assertThat(result).hasSize(1);
            HotelTestUtil.assertDtoEqualsModel(result.getFirst(), sampleHotel);

            mockedStatic.verify(() -> HotelSpecificationUtil.fromSearchDTO(sampleSearchDTO));
            verify(repository).findAll(mockSpecification);
            verify(mapper).toShortDto(sampleHotel);
        }
    }

    @Test
    void get_WithSearchDTO_ShouldReturnEmptyList_WhenNoMatches() {
        Specification<Hotel> mockSpecification = mock(Specification.class);

        try (var mockedStatic = mockStatic(HotelSpecificationUtil.class)) {
            mockedStatic.when(() -> HotelSpecificationUtil.fromSearchDTO(sampleSearchDTO))
                    .thenReturn(mockSpecification);
            when(repository.findAll(mockSpecification)).thenReturn(Collections.emptyList());

            List<ShortResponseHotelDTO> result = hotelService.get(sampleSearchDTO);

            assertThat(result).isEmpty();
            verify(repository).findAll(mockSpecification);
            verify(mapper, never()).toShortDto(any());
        }
    }

    // getHistogram()
    @ParameterizedTest
    @CsvSource({
            "brand, brand",
            "BRAND, brand",
            "  brand  , brand",
            "city, city",
            "country, country"
    })

    void getHistogram_ShouldReturnHistogramResponse_WhenValidFieldName(String inputField, String expectedField) {
        when(repository.getHistogram(expectedField)).thenReturn(sampleHistogramData);

        HistogramResponseDTO result = hotelService.getHistogram(inputField);

        assertThat(result).isNotNull();
        assertThat(result.getData()).hasSize(2);
        assertThat(result.getData().getFirst().getColumnValue()).isEqualTo("Hilton");
        assertThat(result.getData().getFirst().getCount()).isEqualTo(10L);

        verify(repository).getHistogram(expectedField);
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "unknown", "test", "address", "phone", "email"})
    void getHistogram_ShouldThrowInvalidInputException_WhenInvalidFieldName(String invalidField) {
        assertThatThrownBy(() -> hotelService.getHistogram(invalidField))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining(invalidField + " is not a recognized field name!");

        verify(repository, never()).getHistogram(anyString());
    }

    @Test
    void getHistogram_ShouldHandleEmptyResult() {
        when(repository.getHistogram("brand")).thenReturn(Collections.emptyList());

        HistogramResponseDTO result = hotelService.getHistogram("brand");

        assertThat(result).isNotNull();
        assertThat(result.getData()).isEmpty();
        verify(repository).getHistogram("brand");
    }


    @Test
    void addAmenities_ShouldAddAmenities_WhenValidInput() {
        List<String> newAmenities = Arrays.asList("Spa", "Gym", "Pool");
        List<String> existingAmenities = new ArrayList<>(Arrays.asList("Free WiFi", "Free parking"));
        sampleHotel.setAmenities(existingAmenities);

        when(repository.findById(1L)).thenReturn(Optional.of(sampleHotel));
        when(repository.save(sampleHotel)).thenReturn(sampleHotel);

        hotelService.addAmenities(1L, newAmenities);

        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(repository).save(hotelCaptor.capture());

        Hotel savedHotel = hotelCaptor.getValue();
        assertThat(savedHotel.getAmenities()).containsExactlyInAnyOrder(
                "Free WiFi", "Free parking", "Spa", "Gym", "Pool"
        );
    }

    @Test
    void addAmenities_ShouldFilterOutBlankStrings() {
        List<String> newAmenitiesWithBlanks = Arrays.asList("Spa", "", "Gym", "   ", "Pool");
        List<String> existingAmenities = new ArrayList<>(List.of("Free WiFi"));
        sampleHotel.setAmenities(existingAmenities);

        when(repository.findById(1L)).thenReturn(Optional.of(sampleHotel));
        when(repository.save(sampleHotel)).thenReturn(sampleHotel);

        hotelService.addAmenities(1L, newAmenitiesWithBlanks);

        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(repository).save(hotelCaptor.capture());

        Hotel savedHotel = hotelCaptor.getValue();
        assertThat(savedHotel.getAmenities()).containsExactlyInAnyOrder(
                "Free WiFi", "Spa", "Gym", "Pool"
        );
        assertThat(savedHotel.getAmenities()).doesNotContain("", "   ");
    }

    @Test
    void addAmenities_ShouldAddToExistingAmenities_WhenHotelAlreadyHasSome() {
        List<String> existingAmenities = new ArrayList<>(Arrays.asList("Free WiFi", "Free parking"));
        List<String> newAmenities = Arrays.asList("Free WiFi", "Pool"); // Duplicate Free WiFi

        sampleHotel.setAmenities(existingAmenities);

        when(repository.findById(1L)).thenReturn(Optional.of(sampleHotel));
        when(repository.save(sampleHotel)).thenReturn(sampleHotel);

        hotelService.addAmenities(1L, newAmenities);

        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(repository).save(hotelCaptor.capture());

        Hotel savedHotel = hotelCaptor.getValue();
        // Should contain all unique amenities
        assertThat(savedHotel.getAmenities()).containsExactlyInAnyOrder(
                "Free WiFi", "Free parking", "Pool"
        );
    }

    @Test
    void addAmenities_ShouldThrowInvalidInputException_WhenAmenitiesListIsEmpty() {
        assertThatThrownBy(() -> hotelService.addAmenities(1L, Collections.emptyList()))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Amenities list can't be empty!");

        verify(repository, never()).findById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void addAmenities_ShouldThrowEntityDoesntExistException_WhenHotelNotFound() {
        long nonExistentId = 999L;
        when(repository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hotelService.addAmenities(nonExistentId, sampleAmenities))
                .isInstanceOf(EntityDoesntExistException.class)
                .hasMessageContaining("Hotel with id " + nonExistentId + "doesn't exist");

        verify(repository).findById(nonExistentId);
        verify(repository, never()).save(any());
    }

    @Test
    void addAmenities_ShouldHandleEmptyAmenitiesListInHotel() {
        sampleHotel.setAmenities(new ArrayList<>()); // Empty list
        List<String> newAmenities = Arrays.asList("Spa", "Gym");

        when(repository.findById(1L)).thenReturn(Optional.of(sampleHotel));
        when(repository.save(sampleHotel)).thenReturn(sampleHotel);

        hotelService.addAmenities(1L, newAmenities);

        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(repository).save(hotelCaptor.capture());

        Hotel savedHotel = hotelCaptor.getValue();
        assertThat(savedHotel.getAmenities()).containsExactly("Spa", "Gym");
    }
}