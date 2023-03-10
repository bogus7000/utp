package eu.glowacki.utp.assignment04;

import java.util.Date;
import java.util.List;

public final class PersonDatabase {

	private final List<Person> _people;

	public PersonDatabase(List<Person> people) { //
		_people = people;
	}

	public List<Person> sortedByFirstName() { //
		return null; // external rule for ordering (based on Comparator --- FirstNameComparator)
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { //
		return null; // natural order (Comparable)
	}
	
	public List<Person> sortedByBirthdate() { //
		return null; // external rule for ordering (based on Comparator --- BirthdateComparator)
	}
	
	public List<Person> bornOnDay(Date date) { //
		return null;
	}
}