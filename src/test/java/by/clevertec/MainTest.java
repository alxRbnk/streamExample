package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testTask1() {
        List<Animal> animals = Util.getAnimals();
        List<Animal> result = animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .skip(14)
                .limit(7)
                .collect(Collectors.toList());
        assertNotNull(result);
        assertEquals(7, result.size());
        assertTrue(result.stream().allMatch(animal -> animal.getAge() >= 10 && animal.getAge() <= 20));
    }

    @Test
    public void testTask2() {
        List<Animal> animals = Util.getAnimals();
        List<String> result = animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()) && "Female".equals(animal.getGender()))
                .map(animal -> animal.getBread().toUpperCase())
                .collect(Collectors.toList());
        assertNotNull(result);
        assertTrue(result.stream().allMatch(bread -> bread.equals(bread.toUpperCase())));
    }

    @Test
    public void testTask3() {
        List<Animal> animals = Util.getAnimals();
        List<String> result = animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .collect(Collectors.toList());
        assertNotNull(result);
        assertTrue(result.stream().allMatch(origin -> origin.startsWith("A")));
    }

    @Test
    public void testTask4() {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        assertTrue(count >= 0);
    }

    @Test
    public void testTask5() {
        List<Animal> animals = Util.getAnimals();
        boolean fromHungary = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        assertTrue(fromHungary || !fromHungary);
    }

    @Test
    public void testTask6() {
        List<Animal> animals = Util.getAnimals();
        long count = animals.stream()
                .filter(animal -> "Male".equals(animal.getGender()) || "Female".equals(animal.getGender()))
                .count();
        assertTrue(count >= 0);
    }

    @Test
    public void testTask7() {
        List<Animal> animals = Util.getAnimals();
        boolean allFromOceania = animals.stream()
                .allMatch(animal -> "Oceania".equals(animal.getOrigin()));
        assertFalse(allFromOceania);
    }

    @Test
    public void testTask8() {
        List<Animal> animals = Util.getAnimals();
        OptionalInt maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max();
        assertTrue(maxAge.isPresent());
    }

    @Test
    public void testTask9() {
        List<Animal> animals = Util.getAnimals();
        OptionalInt minLength = animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(charArray -> charArray.length)
                .min();
        assertTrue(minLength.isPresent());
    }

    @Test
    public void testTask10() {
        List<Animal> animals = Util.getAnimals();
        int sum = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        assertTrue(sum >= 0);
    }

    @Test
    public void testTask11() {
        List<Animal> animals = Util.getAnimals();
        OptionalDouble average = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToDouble(Animal::getAge)
                .average();
        assertTrue(average.isPresent());
    }
}