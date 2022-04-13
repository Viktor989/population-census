package ViktorGolikov;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Петр", "Виктор", "Ирина", "Егор", "Игорь", "Юлия");
        List<String> families = Arrays.asList("Иванов", "Петров", "Сидоров", "Петросян", "Пугачева", "Кабзон", "Львов");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(х -> х.getAge() < 18)
                .count();
        System.out.println("Колличество молодых людей до 18 лет");
        System.out.println(count + " человек");
        System.out.println("Фамилии молодых людей призывного возраста");
        List<String> conscripts = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(y -> y.getAge() <= 27)
                .filter(z -> z.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscripts);

        System.out.println("Список потенциально работоспособного населения");
        List<Person> workableMen = persons.stream()
                .filter(x -> x.getAge() >= 18 )
                .filter(y -> y.getAge() <= 60 && y.getSex().equals(Sex.MAN))
                .filter(z -> z.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workableMen);
        List<Person> workableWoman = persons.stream()
                .filter(x -> x.getAge() >= 18 )
                .filter(i -> i.getAge() <= 55 && i.getSex().equals(Sex.WOMAN))
                .filter(z -> z.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workableWoman);

    }
}
