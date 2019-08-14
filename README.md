# OOB Payroll Project

## Observations
 Required project to obtain AB1 grade for the Software Design course, from the course of computer science at the Federal University of Alagoas, taught by Professor Baldoino Fonseca.
 
 This project was a refactoring using some of the object orientation concepts, from the following [project](https://github.com/joaorura/Payroll) and [this](https://github.com/joaorura/Payroll-OOB/blob/master/Payroll-OOB%20(Older%20version).zip).

### Refactoring details
 In this project the strategy, extract method, extract class and singleton design patterns were used in the following features:
 
 - Strategy -> In view part and a feature of model. 
 - Singleton -> In controller part, specifically in classes: Payroll, Employee and System controllers
 - Extract Class -> In singleton classes we used.
 - Extract Method -> In several project classes, however, the most noticeable was in the Utils classes.
   
#### Some considerations were taken in the creation of the project, are listed below:

  - Salaried and commissioned employees, receive their monthly salary consistent with the work days (Marked via dot).

  - Commissioners can receive their bandages normally.

  - All debts of the employee will be debited in the next sheet, with the proviso that they can not receive "negative" wages; so if you keep this debit will be placed on the next sheet.

#### The reference taken for the creation of the project is found in priciples.pdf within this repository.

##### University info:
 - Name: João Messias Lima Pereira
 - Course: Computer Science
 - Registration number: 18110470
