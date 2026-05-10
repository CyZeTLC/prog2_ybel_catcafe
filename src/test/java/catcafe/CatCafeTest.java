package catcafe;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    void setUp() {
        cafe = new CatCafe();
    }

    @Test
    void shouldBeEmptyInitially() {
        // Given: A fresh CatCafe (see setUp)

        // When: getCatCount is called
        long count = cafe.getCatCount();

        // Then: The count should be 0
        assertEquals(0, count);
    }

    @Test
    void shouldIncreaseCountWhenCatAdded() {
        // Given: One cat
        FelineOverLord luna = new FelineOverLord("Luna", 4);

        // When: Adding the cat
        cafe.addCat(luna);

        // Then: Count should be 1
        assertEquals(1, cafe.getCatCount());
    }

    @Test
    void shouldFindCatByName() {
        // Given: A cafe with a cat named "Simba"
        cafe.addCat(new FelineOverLord("Simba", 5));

        // When: Searching for "Simba"
        FelineOverLord found = cafe.getCatByName("Simba");

        // Then: The correct cat should be returned
        assertNotNull(found);
        assertEquals("Simba", found.name());
    }

    @Test
    void shouldReturnNullWhenCatNameNotFound() {
        // Given: A cafe with a cat
        cafe.addCat(new FelineOverLord("Simba", 5));

        // When: Searching for a non-existent name "Garfield"
        FelineOverLord found = cafe.getCatByName("Garfield");

        // Then: Result should be null
        assertNull(found);
    }

    @Test
    void shouldReturnNullWhenSearchingForNullName() {
        // When: Searching for null
        FelineOverLord found = cafe.getCatByName(null);

        // Then: Result should be null
        assertNull(found);
    }

    @Test
    void shouldFindCatWithinWeightRange() {
        // Given: A cat with 4kg
        cafe.addCat(new FelineOverLord("Mimi", 4));

        // When: Searching for weight 3 to 5
        FelineOverLord found = cafe.getCatByWeight(3, 5);

        // Then: Mimi should be found
        assertNotNull(found);
        assertEquals("Mimi", found.name());
    }

    @Test
    void shouldFindCatOnLowerWeightBoundary() {
        // Given: A cat with exactly 5kg
        cafe.addCat(new FelineOverLord("Balu", 5));

        // When: Searching with minWeight 5 (inclusive)
        FelineOverLord found = cafe.getCatByWeight(5, 10);

        // Then: Balu should be found
        assertNotNull(found);
    }

    @Test
    void shouldNotFindCatOnUpperWeightBoundary() {
        // Given: A cat with exactly 10kg
        cafe.addCat(new FelineOverLord("Rex", 10));

        // When: Searching with maxWeight 10 (exclusive)
        FelineOverLord found = cafe.getCatByWeight(5, 10);

        // Then: Rex should not be found
        assertNull(found);
    }

    @Test
    void shouldReturnNullForInvalidWeightRange() {
        // When: minWeight is higher than maxWeight
        FelineOverLord found = cafe.getCatByWeight(10, 5);

        // Then: Result should be null
        assertNull(found);
    }

    @Test
    void shouldThrowExceptionWhenAddingNullCat() {
        // When/Then: Adding null should throw NullPointerException
        assertThrows(NullPointerException.class, () -> {
            cafe.addCat(null);
        });
    }
}
