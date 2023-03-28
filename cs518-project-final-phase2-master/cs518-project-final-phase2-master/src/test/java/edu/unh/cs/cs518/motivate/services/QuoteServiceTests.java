package edu.unh.cs.cs518.motivate.services;

import edu.unh.cs.cs518.motivate.models.Quote;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import edu.unh.cs.cs518.motivate.models.InvalidAuthorException;

/**
 * Series of JUnit Tests for the QuoteService API.
 * QuoteService is supported by multiple Controller classes.
 * Underlying classes should be assumed error-free.
 */

public class QuoteServiceTests {

    @Test
    void GetQuote_WhenCalled_DoesNotReturnEmptyOptional () {
        QuoteService test = new QuoteService();

        assertThat(test.getQuote().equals(Optional.empty()), is(false));
    }

    @Test
    void GetQuote_NonExistentAuthor_ReturnsEmptyFields () {
        // if nonexistent author is called, then value returned should have empty fields
        QuoteService test = new QuoteService();

        assertThat(test.getQuote("non_author").equals(Optional.empty()), is(true));

    }

    @Test
    void GetQuote_ExistingAuthor_ReturnsQuoteFromThatAuthor () {
        QuoteService test = new QuoteService();

        assertThat(test.getQuote("Benjamin Franklin").isPresent(), is(true));

        assertThat(test.getQuote("Benjamin Franklin").get().author, is("Benjamin Franklin"));
    }

}
