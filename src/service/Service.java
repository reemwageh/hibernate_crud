package service;

import domain.Student;

public interface Service {
    public void addNewStudent(Student student);
    public void readDataOfStudent();
    public void updateDataOfStudent(Long id, String newName, String newEmail, Integer newAge);
    public void deleteStudentById(Long id);

}
