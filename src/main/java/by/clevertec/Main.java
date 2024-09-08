package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted((Comparator.comparingInt(Animal::getAge)))
                .skip(14)
                .limit(7)
                .forEach(System.out::println);
        System.out.println("separator line 1 --------------");
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()) && "Female".equals(animal.getGender()))
                .map(animal -> animal.getBread().toUpperCase())
                .forEach(System.out::println);
        System.out.println("separator line 2 --------------");
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
        System.out.println("separator line 3 --------------");
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        long females = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.println(females);
        System.out.println("separator line 4 --------------");
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        boolean fromHungary = animals.stream()
                .filter(animal -> animal.getAge() >= 20)
                .filter(animal -> animal.getAge() <= 30)
                .anyMatch(origin -> origin.getOrigin().equals("Hungarian"));
        System.out.println(fromHungary);
        System.out.println("separator line 5 --------------");
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        long animalsWithGender = animals.stream()
                .filter(animal -> {
                    String gender = animal.getGender();
                    return gender.equals("Male") || gender.equals("Female");
                })
                .count();
        System.out.println(animalsWithGender);
        System.out.println("separator line 6 --------------");
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean fromOceania = animals.stream()
                .allMatch(animal -> animal.equals("Oceania"));
        System.out.println(fromOceania);
        System.out.println("separator line 7 --------------");
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .map(Animal::getAge)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
        System.out.println("separator line 8 --------------");
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .map(charArray -> charArray.length)
                .min(Integer::compareTo)
                .ifPresent(System.out::println);
        System.out.println("separator line 9 --------------");
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        int sum = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println(sum);
        System.out.println("separator line 10 --------------");
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToDouble(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
        System.out.println("separator line 11 --------------");
    }

    public static void task12() {
        LocalDate eighteen = LocalDate.now().minusYears(18);
        LocalDate twentySeven = LocalDate.now().minusYears(27);
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> person.getDateOfBirth().isBefore(eighteen))
                .filter(person -> person.getDateOfBirth().isAfter(twentySeven))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
        System.out.println("separator line 12 --------------");
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        List<Person> people = houses.stream()
                .flatMap(house -> house.getPersonList().stream())
                .toList();
        List<Person> prioritizedPeople = people.stream()
                .sorted(Comparator.comparing(person -> {
                    House house = houses.stream()
                            .filter(h -> h.getPersonList().contains(person))
                            .findFirst()
                            .orElse(null);
                    if (house == null) return 4;
                    boolean isHospital = house.getBuildingType().equals("Hospital");
                    boolean isYoung = LocalDate.now().getYear() - person.getDateOfBirth().getYear() < 18;
                    boolean isOld = LocalDate.now().getYear() - person.getDateOfBirth().getYear() >= 65;
                    if (isHospital) {
                        return 1;
                    }
                    if (isYoung || isOld) {
                        return 2;
                    }
                    return 3;
                }))
                .limit(500)
                .toList();
        prioritizedPeople.forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName()));
        System.out.println("separator line 13 --------------");
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        final double transportCostPerTon = 7.14;
        List<String> countries = Arrays.asList(
                "Turkmenistan", "Uzbekistan", "Kazakhstan", "Kyrgyzstan", "Russia", "Mongolia"
        );
        Predicate<Car> firstEchelon = car -> "Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor());
        Predicate<Car> secondEchelon = car -> car.getMass() <= 1500 && Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake());
        Predicate<Car> thirdEchelon = car -> "Black".equals(car.getColor()) && car.getMass() > 4000 || Arrays.asList("GMC", "Dodge").contains(car.getCarMake());
        Predicate<Car> fourthEchelon = car -> car.getReleaseYear() < 1982 || Arrays.asList("Civic", "Cherokee").contains(car.getCarModel());
        Predicate<Car> fifthEchelon = car -> !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) || car.getPrice() > 40000;
        Predicate<Car> sixthEchelon = car -> car.getVin().contains("59");
        Map<String, Integer> countryMassMap = countries.stream()
                .collect(Collectors.toMap(country -> country, country -> 0));
        Map<String, Double> countryCostMap = countries.stream()
                .collect(Collectors.toMap(country -> country, country -> 0.0));
        Map<String, List<Car>> carsByEchelon = new HashMap<>();
        carsByEchelon.put("First Echelon", cars.stream().filter(firstEchelon).collect(Collectors.toList()));
        carsByEchelon.put("Second Echelon", cars.stream().filter(secondEchelon).collect(Collectors.toList()));
        carsByEchelon.put("Third Echelon", cars.stream().filter(thirdEchelon).collect(Collectors.toList()));
        carsByEchelon.put("Fourth Echelon", cars.stream().filter(fourthEchelon).collect(Collectors.toList()));
        carsByEchelon.put("Fifth Echelon", cars.stream().filter(fifthEchelon).collect(Collectors.toList()));
        carsByEchelon.put("Sixth Echelon", cars.stream().filter(sixthEchelon).collect(Collectors.toList()));
        carsByEchelon.forEach((echelon, echelonCars) -> {
            int totalMass = echelonCars.stream().mapToInt(Car::getMass).sum();
            double totalCost = totalMass / 1000.0 * transportCostPerTon;
            countryMassMap.replaceAll((country, mass) -> mass + totalMass);
            countryCostMap.replaceAll((country, cost) -> cost + totalCost);
        });
        double totalRevenue = countryCostMap.values().stream().mapToDouble(Double::doubleValue).sum();
        countryCostMap.forEach((country, cost) -> {
            System.out.println("Transport cost for " + country + ": $" + cost);
        });
        System.out.println("Total $" + totalRevenue);
        System.out.println("separator line 14 --------------");
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        final double waterCostPerCubicMeter = 1.39;
        List<Flower> sortedFlowers = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Flower::getWaterConsumptionPerDay, Comparator.reverseOrder()))
                .toList();
        List<Flower> filteredFlowers = sortedFlowers.stream()
                .filter(flower -> {
                    String name = flower.getCommonName();
                    return name.compareToIgnoreCase("S") >= 0 && name.compareToIgnoreCase("C") <= 0;
                })
                .filter(flower -> flower.isShadePreferred() &&
                        flower.getFlowerVaseMaterial().stream().anyMatch(material ->
                                Arrays.asList("Glass", "Aluminum", "Steel").contains(material)))
                .toList();
        double totalCost = filteredFlowers.stream()
                .mapToDouble(flower -> {
                    double plantCost = flower.getPrice();
                    double waterCost = flower.getWaterConsumptionPerDay() * 365 * 5 * waterCostPerCubicMeter;
                    return plantCost + waterCost;
                })
                .sum();
        System.out.println("Total $" + totalCost);
        System.out.println("separator line 15 --------------");
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + " Age " + student.getAge()));
        System.out.println("separator line 16 --------------");
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        Set<String> uniqueGroups = students.stream()
                .map(Student::getGroup)
                .collect(Collectors.toSet());
        uniqueGroups.forEach(System.out::println);
        System.out.println("separator line 17 --------------");
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Map<String, Double> averageAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)
                ));
        averageAgeByFaculty.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Faculty: " + entry.getKey() + " - Average Age: " + entry.getValue()));
        System.out.println("separator line 18 --------------");
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        String targetGroup = "P-1";
        List<Integer> studentIdsInGroup = students.stream()
                .filter(student -> student.getGroup().equals(targetGroup))
                .map(Student::getId)
                .toList();
        List<Integer> studentsWithAllPassedExams = examinations.stream()
                .filter(exam -> exam.getExam1() > 4 && exam.getExam2() > 4 && exam.getExam3() > 4)
                .map(Examination::getStudentId)
                .toList();
        List<Student> filteredStudents = students.stream()
                .filter(student -> studentIdsInGroup.contains(student.getId()) &&
                        studentsWithAllPassedExams.contains(student.getId()))
                .toList();
        filteredStudents.forEach(student ->
                System.out.println("Student ID: " + student.getId() +
                        ", Name: " + student.getSurname() +
                        ", Age: " + student.getAge() +
                        ", Group: " + student.getGroup()));
        System.out.println("separator line 19 --------------");
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Map<String, Double> averageScoresByFaculty = students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingDouble(student -> {
                            Examination examination = examinations.stream()
                                    .filter(exam -> exam.getStudentId() == student.getId())
                                    .findFirst()
                                    .orElse(null);
                            return examination != null ? examination.getExam1() : 0.0;
                        })
                ));
        Optional<Map.Entry<String, Double>> maxAverageFaculty = averageScoresByFaculty.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        maxAverageFaculty.ifPresent(entry ->
                System.out.println("Faculty: " + entry.getKey() + ", Average Score: " + entry.getValue()));
        System.out.println("separator line 20 --------------");
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        Map<String, Long> studentsByGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()));
        studentsByGroup.forEach((group, count) ->
                System.out.println("Group: " + group + ", Number of students: " + count));
        System.out.println("separator line 21 --------------");
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        Map<String, Optional<Integer>> minAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.mapping(Student::getAge, Collectors.minBy(Integer::compareTo))
                ));
        minAgeByFaculty.forEach((faculty, minAge) ->
                System.out.println("Faculty: " + faculty + ", Minimum age: " + minAge.orElse(-1)));
        System.out.println("separator line 22 --------------");
    }
}
