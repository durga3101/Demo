package com.trailblazers.freewheelers.service;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CountryReaderTest {

    private BufferedReader bufferedReader;
    private CountryReader countryReader;

    @Before
    public void setUp() throws Exception {
        bufferedReader = mock(BufferedReader.class);
        countryReader = new CountryReader(bufferedReader);
    }

    @Test
    public void shouldReturnReadLineFromFile() throws IOException {
        countryReader.getCountries();
        verify(bufferedReader).readLine();
    }

    @Test
    public void shouldReturn3CountriesIfFiloContain3Countries() throws IOException {
        when(bufferedReader.readLine()).thenReturn("UK").thenReturn("USA").thenReturn("INDIA").thenReturn(null);
        countryReader.getCountries();
        verify(bufferedReader, times(4)).readLine();
    }

    @Test
    public void shouldReturnEmptyArrayOfCountriesIfCouldNotReadTheFile() throws IOException {
        when(bufferedReader.readLine()).thenThrow(IOException.class);
        assertEquals("", countryReader.getCountries()[0]);
    }


}
