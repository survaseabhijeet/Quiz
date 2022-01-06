package com.JdbcConnection;

import java.sql.*; 

import java.util.*;
public class RetriveData {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static int Score=0;
	
 
			public static void main(String[] args) throws SQLException {
				//Student student =new Student();
			 System.out.println("Enter your Student id");
	 	 	 Scanner sc1=new Scanner(System.in);
	 	 	 //student.setStudent_id(sc1.nextInt());
	 	 	 
	 	 	 int Student_id=sc1.nextInt();
	 	     System.out.println("Enter your Name");
	 	     String Student_name=sc1.next();
 	 	    // student.setStudent_name(sc1.next());
	 	     try { 
 	 	 	 
 	 	 	Class.forName("com.mysql.jdbc.Driver");
 	 	 
 	 	 	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","abhijeet@1234"); 
 	 	 	 
 	 	 
	 	 	ps=con.prepareStatement("insert into studentinfo values(?,?)");  
	 	 	ps.setInt(1,Student_id);//1 specifies the first parameter in the query  
	 	 	ps.setString(2,Student_name);  
	 	 	  
	 	 	int i=ps.executeUpdate();
		    System.out.println("\n Now you can take this quiz!!! HERE WE GO\n");

 	 	 	ps=con.prepareStatement("select * from Questions order by rand() "); 
 	 	 	ResultSet rs=ps.executeQuery();
 	 	 	int count=1;//forquetionnumbers123etc
 	 	 	 
 	 	 	while(rs.next()) { 
 	 	 	 	 
 	 	 		System.out.println("QuestionNumber="+count);
 	 	 		System.out.println("\n"+rs.getString(2));//questions
 	 	 		System.out.println("OptionA="+rs.getString(3));//options
 	 	 		System.out.println("OptionB="+rs.getString(4));
 	 	 		System.out.println("OptionC="+rs.getString(5));
 	 	 		System.out.println("OptionD="+rs.getString(6));
 	 	 		System.out.println("Enter your answer");
 	 	 		Scanner sc=new Scanner(System.in);
 	 	 		String Answer=sc.next();
 	            Answer = Answer.toUpperCase();
 	            
 	           String s=rs.getString(7);
 	          if(Answer.equals(s))
 	          {
 	          System.out.println("Correct..You Are Doing Great!!!");
 	          Score++;
 	          }
 	          else{
 	          System.out.println("Wrong Answer..Focus Budy!!!");
 	         }
 	         System.out.println("\n");
 	         count++;
 	         }
 	         }//try close
 	         catch(Exception e){
 	         e.printStackTrace();
 	         }
	 	    finally  {
          	  con.close();
          	  ps.close();
          	  
            }
		
	 	    try{
	 	    	System.out.println("Your Score="+Score);
	 	    	if(Score>=8&&Score<=10)
	 	    	{
	 	    	ps=con.prepareStatement("insert into scoreinfo values(?,?,?)");
	 	    	ps.setInt(1,Score);
	 	    	ps.setString(2,"A");
	 	    	ps.setInt(3,Student_id);
	 	    	int i=ps.executeUpdate();
	 	    	System.out.println("\nCongratulations You are Class A Performer");
	 	    	}
	 	    	else if(Score>=6&&Score<8)
	 	    	{
	 	    	ps=con.prepareStatement("insert into scoreinfo values(?,?,?)");
	 	    	ps.setInt(1,Score);
	 	    	ps.setString(2,"B");
	 	    	ps.setInt(3,Student_id);
	 	    	int i=ps.executeUpdate();
	 	    	System.out.println("\nCongratulations You are Class B Performer");
	 	    	}
	 	    	else if(Score==5)
	 	    	{ ps=con.prepareStatement("insert into scoreinfo values(?,?.?)");
	 	    	ps.setInt(1,Score);
	 	    	ps.setString(2,"C");
	 	    	ps.setInt(3,Student_id);
	 	
	 	    	
	 	    	int i=ps.executeUpdate();
	 	    	System.out.println("\nCongratulations You are Class C Performer");
	 	    	}
	 	    	else if(Score<5){
	 	    	ps=con.prepareStatement("insert into scoreinfo values(?,?,?)");
	 	    	ps.setInt(1,Score);
	 	    	ps.setString(2,"Fail");
	 	    	ps.setInt(3,Student_id);
	 	    	int i=ps.executeUpdate();
	 	    	System.out.println("\nSorry You Failed in this Quiz!!!All  the Best For Next Time!!!");
	 	    	}	
	 	    	
	 	    	System.out.println("\nYour record is preserved for further references");
	 	    	System.out.println("Do you want to see it now..Please type Y|N");
	 	    	Scanner sc=new Scanner(System.in);
	 	    	String input=sc.next();
	 	    	if(input.equals("y"))
	 	    	{
	 	    	ps=con.prepareStatement("select* from studentinfo left join scoreinfo on studentinfo.student_id=scoreinfo.student_id union select* from studentinfo right join scoreinfo on scoreinfo.student_id=studentinfo.student_id order by score desc");
	 	    	ResultSet rs=ps.executeQuery();
	 	    	while(rs.next()){
	 	    	System.out.println("Student_id="+rs.getInt(1) + "--> Student Name="+rs.getString(2) + "-->Score="+rs.getString(3)+"-->Grade="+rs.getString(4));
	 	    	}
	 	    	}
	 	    	else{
	 	    	System.out.println("ThankYou!!!PlayAgain");
	 	    	}
	 	    	}//try complete
	 	    	catch(Exception e){
	 	    	e.printStackTrace();
	 	    	}
	
		
	 	    	}
    }
	

