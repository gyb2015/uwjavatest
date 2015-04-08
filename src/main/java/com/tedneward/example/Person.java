package com.tedneward.example;

import java.beans.*;
import java.util.*;
import java.lang.*;

public class Person implements Comparable<Person> {
  private static int count = 0;

  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    if (n == null) {
      throw new IllegalArgumentException("firstName can't be null.");
    }
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  // 1. getters and setters
  public int getAge() {
    return age;
  }
  
  // 2. set person age
  public void setAge(int a) {
    if (a < 0) {
      throw new IllegalArgumentException("Age can't be negative.");
    }
    age = a;
  }
  
  public String getName() { 
    return name;
  }
  
  // 3. set name for person
  public void setName(String n) {
    if (n == null) {
      throw new IllegalArgumentException("Name can't be null.");
    }
    name = n;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public void setSalary(double s) {
    this.salary = s;
  }
  
  public String getSSN() {
    return ssn;
  }
  
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;

    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }
  
  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }

  // 4. count total number of people
  public int count() {
    return count;
  }
  
  // 5. compare persons with same name and age, avoid duplicate
  public boolean equals(Object other) {
    try {
      Person p = (Person)other;
      return (this.name.equals(p.name) && this.age == p.age);
    }
    catch (Exception e) {
      return false;
    }
  }
  
  // 6. nested class AgeComparator
  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person other1, Person other2) {
      return (other1.getAge() - other2.getAge());
    }
  }
  
  // 7. make Person be comparable in reverse order
  public int compareTo(Person anotherPerson) {
    if (anotherPerson.salary < this.salary) {
      return -1;
    }
    else if (anotherPerson.salary > this.salary) {
      return 1;
    }
    return 0;
  }
  
  // 8. create a static method "getNewardFamily"
  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted", 41, 250000));
    family.add(new Person("Charlotte", 43, 150000));
    family.add(new Person("Michael", 22, 10000));
    family.add(new Person("Matthew", 15, 0));
    return family;
  }
  
  // 9. implement a final test (see TestPerson.java)
  
  // 10. implement toString for TestPerson
  public String toString() {
    return String.format("[Person name:%s age:%d salary:%.2f]", name, age, salary);
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    this.pcs.removePropertyChangeListener(listener);
  }
}
