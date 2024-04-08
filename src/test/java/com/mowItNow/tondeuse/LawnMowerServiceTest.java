package com.mowItNow.tondeuse;

import com.mowItNow.tondeuse.service.LawnMowerService;
import com.mowItNow.tondeuse.model.Position;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class LawnMowerServiceTest {

    @InjectMocks
    private LawnMowerService lawnMowerService;

    @Test
    void shouldReturnFinalPositionsFromFile() {
        List<Position> positions = lawnMowerService.lawn("src/test/resources/lawn_input.txt");
        assertEquals(2, positions.size());
        assertEquals("1 3 N", positions.get(0).toString());
        assertEquals("5 1 E", positions.get(1).toString());
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        assertThrows(RuntimeException.class, () -> lawnMowerService.lawn("src/test/resources/non_existent_file.txt"));
    }

    @Test
    void shouldReturnEmptyListWhenNoMowersInFile() {
        List<Position> positions = lawnMowerService.lawn("src/test/resources/empty_garden.txt");
        assertEquals(0, positions.size());
    }

    @Test
    void shouldHandleMowerWithNoInstructions() {
        List<Position> positions = lawnMowerService.lawn("src/test/resources/garden_with_mower_no_instructions.txt");
        assertEquals(1, positions.size());
        assertEquals("1 2 N", positions.get(0).toString());
    }
}
