@Student
Feature: Student Creation
				 This feature will create a student and will validate 
				 the created student information

		Scenario: Create and validate a student
			When I create a new student and store the student id
			And I get the student using the stored id
			Then I validate the information of the retrieved student
			
