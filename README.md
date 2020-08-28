# 🛠️ LABORATORIO 3
  
  📌 **Angi Paola Jiménez Pira**
  
  
## Introduction to Spring and Configuration using annotations
  
### Part I - Basic workshop 

   To illustrate the use of the Spring framework, and the development environment for its use through Maven (and NetBeans), the configuration of a text analysis application
   will be made, which makes use of a grammar verifier that requires a spelling checker. The grammar checker will be injected, at the time of execution, with the spelling
   checker required (for now, there are two available: English and Spanish).
   
   1. Open the project sources in NetBeans.
   
   2. Review the Spring configuration file already included in the project (src / main / resources). It indicates that Spring will automatically search for the 'Beans'
   available in the indicated package.
   
       `<context:component-scan base-package="edu.eci.arsw"/>`  permite detectar todas las anotaciones o estereotipos de las clases del paquete edu.eci.arsw 
   
   3. Making use of the Spring configuration based on annotations mark with the annotations @Autowired and @Service the dependencies that must be injected, and the 'beans'
   candidates to be injected -respectively-:
   
         1. GrammarChecker will be a bean, which depends on something like 'SpellChecker'.
	 
         2. EnglishSpellChecker and SpanishSpellChecker are the two possible candidates to be injected. One must be selected, or another, but NOT both (there would be
	 dependency resolution conflict). For now, have EnglishSpellChecker used.
	 
	  ***SOLUCIÓN***
	 
	   * Como GrammarChecker es un bean, hay dos opciones: Definir el bean en el archivo xml o poner el estereotipo `@Component` en la clase que será el bean, como ya
	   está especificado en el xml que se detectarán todas las anotaciones y estereotipos, usaremos el estereotipo. Teniendo en cuenta que `@Service` es una
	   especialización de `@Component` para la capa de negocio, usaremos esa:
		 
		    ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/service1.png)
		 
	   * En las clases EnglishSpellChecker y SpanishSpellChecker también usaremos la anotación `@Service`, pero en estas especificaremos un nombre para el bean ya que 
	   esto lo vamos a usar en el siguiente paso:
		 
		    ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/service2.png)
		    
		    ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/service3.png)
		 
	   *  Usamos la anotación `@Autowired` en las propiedades que deseamos inyectar, en este caso será en el atributo sc de la clase SpellChecker, como este puede ser  de 
	   tipo EnglishSpellChecker o SpanishSpellChecker, usamos la anotación `@Quialifier("nombreDelBean")` para especificar cual será: 
		 
		    ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/autowiredd.png) 
	   
	   * Salida:
	   
		    ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/outputenglish.png) 
	 
   4. Make a test program, where an instance of GrammarChecker is created by Spring, and use it:
   
   	      public static void main(String[] args) {
	         ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	         GrammarChecker gc=ac.getBean(GrammarChecker.class);
	         System.out.println(gc.check("la la la "));
          }
	  
	  ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/test1.png) 
	  	  
### Part II

   1. Modify the configuration with annotations so that the Bean 'GrammarChecker' now makes use of the SpanishSpellChecker class (so that GrammarChecker is injected with
   EnglishSpellChecker instead of SpanishSpellChecker.) Verify the new result.
   
      ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/GRAMMAR-CHECKER/img/outputspanish.png) 
   
## Cinema Book System

###  Description

   In this exercise we will build a class model for the logical layer of an application that allows managing the sale of cinema tickets for a prestigious company.
   
   ![alt text](https://raw.githubusercontent.com/angipaola10/LAB3-ARSW/master/CINEMA_I/img/CinemaClassDiagram.png)
   
### Part I

   1. Configure the application to work under a dependency injection scheme, as shown in the previous diagram. The above requires:
   
        1. Add the dependencies of Spring.
	
        2. Add the Spring configuration. 
	
        3. Configure the application -by means of annotations- so that the persistence scheme is injected at the moment of creation of the 'CinemaServices' bean.   
	
   2.Complete the getCinemaByName (), buyTicket (), and getFunctionsbyCinemaAndDate () operations. Implement everything required from the lower layers (for now, the available
   persistence scheme 'InMemoryCinemasPersistence') by adding the corresponding tests in 'InMemoryPersistenceTest'.
   
   3. For later queries, we want to implement two functionalities:
   
        1. A method 'getFunctionsbyCinemaAndDate' that allows to obtain all the functions of a certain cinema for a certain date. 
	
        2. Allow the purchase or reservation of tickets for a certain position of chairs in the room through the 'buyTicket' method. 
	
   4. Make a program in which you create (through Spring) an instance of CinemaServices, and rectify the functionality of it: register cinemas, consult cinemas, obtain the
   functions of certain cinema, buy / book tickets, etc.
  
  5. It is wanted that the consultations realize a filtering process of the films to exhibit, said filters look for to give him the facility to the user to see the 
  mostsuitable films according to his necessity. Adjust the application (adding the abstractions and implementations that you consider) so that the CinemaServices class is
  injected with one of two possible 'filters' (or possible future filters). The use of more than one at a time is not contemplated:
  
        1. (A) Filtered by gender: Allows you to obtain only the list of the films of a certain genre (of a certain cinema and a certain date) (The genre enters by
	parameter). 
	
        2. (B) Filtering by availability: Allows you to obtain only the list of films that have more than x empty seats (of a certain cinema and a certain date) (The number
	of seats is entered per parameter).
   
   6. Add the corresponding tests to each of these filters, and test their operation in the test program, verifying that only by changing the position of the annotations
   -without changing anything else-, the program returns the list of films filtered in the manner (A ) or in the way (B).
   


