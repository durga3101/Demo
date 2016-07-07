package com.trailblazers.freewheelers.service;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CountryReaderTest {
    @Test
    public void shouldReturnReadLineFromFile() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        CountryReader countryReader = new CountryReader(bufferedReader);
        countryReader.getCountries();
        verify(bufferedReader).readLine();
    }

    @Test
    public void shouldReturn3CountriesIfFiloContain3Countries() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenReturn("UK").thenReturn("USA").thenReturn("INDIA").thenReturn(null);
        CountryReader countryReader = new CountryReader(bufferedReader);
        countryReader.getCountries();
        verify(bufferedReader, times(4)).readLine();
    }

    @Test
    public void shouldReturnEmptyArrayOfCountriesIfCouldNotReadTheFile() throws IOException {
        BufferedReader bufferedReader = mock(BufferedReader.class);
        when(bufferedReader.readLine()).thenThrow(IOException.class);
        CountryReader countryReader = new CountryReader(bufferedReader);
        assertEquals("", countryReader.getCountries()[0]);
    }


}
