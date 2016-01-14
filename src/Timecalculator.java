import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
//finds the time before or after a given duration
public class Timecalculator extends Application{

	TextField hour = new TextField(); //sets a text field and label for the hour(user input)
	Label Hour = new Label("Hour");

	TextField minutes = new TextField(); 
	Label Minutes = new Label("Minutes");

	TextField hoursAdded = new TextField(); 
	Label HoursAdded = new Label("Hours to add or subtract (include sign)"); //hours to go back or forward

	TextField minutesAdded = new TextField(); //minutes to modify
	Label MinutesAdded = new Label("Minutes to add");

	Button calculate = new Button("Calculate");
	Label answer = new Label();

	public void start(Stage primaryStage){
		GridPane window = new GridPane();

		window.add(hour, 0, 0);window.add(Hour,0,1); //hour text and label field
		window.add(minutes, 1, 0);window.add(Minutes,1,1); //minutes text and label field

		window.add(hoursAdded, 0, 3);window.add(HoursAdded,0,4); //hours to go back or forward label and field
		window.add(minutesAdded, 1, 3);window.add(MinutesAdded,1,4); //hours to go back or forward label and field

		window.add(calculate,0,6); //calculate button
		window.add(answer,0,8); //answer text field which is initially empty is filled using event handler

		calculate.setStyle("-fx-border-color: black");
		calculate.setOnAction(e -> find());
		Scene scene = new Scene(window,500,400);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args){
		launch(args);
	}

	private void find(){
		boolean validInputs = true; //to check if user provided inputs are valid

		int currentHour = (Integer.parseInt(hour.getText())); //user entered hours(current hours)
		int currentMinutes = (Integer.parseInt(minutes.getText())); //user entered minutes

		int hourChange = Integer.parseInt(hoursAdded.getText())%24; //user entered hour to modify
		int minuteChange = (Integer.parseInt(minutesAdded.getText()))%60; //user entered minutes to modify

		int modifiedHours;
		int modifiedMinutes;
		int h=0; //answer to hours
		int m=0; //answer to minutes

		//negative values
		if(currentHour < 0 || currentMinutes < 0 || minuteChange < 0){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Negative values not permitted");
			alert.showAndWait();
		}

		//if minute values are greater than or equal to 60
		if(currentMinutes >= 60 || minuteChange >= 60){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Minutes can't be greater than 59");
			alert.showAndWait();
		}

		//if minute values are greater than or equal to 60
		if(currentHour >= 24){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Hour value can't be greater than 23");
			alert.showAndWait();
		}

		if(validInputs){

			if (hourChange >= 0)
			{
				modifiedHours = hourChange ;
				modifiedMinutes = minuteChange ;
				if (currentHour + modifiedHours >=24 && currentMinutes + modifiedMinutes < 60)
				{
					h = ((currentHour +hourChange)% 24) ;
					m = (currentMinutes +minuteChange) % 60 ;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes < 60)
				{
					h = ((currentHour +hourChange)% 24) ;
					m = (currentMinutes + minuteChange)%60;
				}

				else if (currentHour + modifiedHours >= 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = ((currentHour +hourChange +1)% 24);
					m = (currentMinutes +minuteChange) % 60 ;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = ((currentHour +hourChange +1)% 24);
					m = (currentMinutes +minuteChange) % 60 ;
				}
			}

			else if (hourChange > -12 && hourChange < 0)
			{
				modifiedHours = 11 + hourChange + ((60-minuteChange)/60) ;
				modifiedMinutes = (60 - minuteChange)%60 ;
				if (currentHour + modifiedHours >=24 && currentMinutes + modifiedMinutes < 60)
				{
					h = ((currentHour +modifiedHours +12)% 24) ;
					m = (modifiedMinutes + currentMinutes)%60 ;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes < 60)
				{
					h = ((currentHour +modifiedHours +12 )% 24) ;
					m = (modifiedMinutes + currentMinutes)%60;
				}

				else if (currentHour + modifiedHours >= 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = ((currentHour +modifiedHours +13)% 24);
					m = (modifiedMinutes + currentMinutes)%60;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = (currentHour +modifiedHours +13)% 24;
					m = (modifiedMinutes + currentMinutes)%60;
				}
			}

			else if (hourChange <= -12)
			{
				modifiedHours = 23 + hourChange + ((60-minuteChange)/60) ;
				modifiedMinutes = (60 - minuteChange)%60 ;

				if (currentHour + modifiedHours >=24 && currentMinutes + modifiedMinutes < 60)
				{
					h = ((currentHour +modifiedHours)% 24) ;
					m = (modifiedMinutes + currentMinutes)%60;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes < 60)
				{
					h = (currentHour +modifiedHours)% 24 ;
					m = (modifiedMinutes + currentMinutes)%60;
				}

				else if (currentHour + modifiedHours >= 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = (currentHour +modifiedHours +1)% 24;
					m = (modifiedMinutes + currentMinutes)%60;
				}

				else if (currentHour + modifiedHours < 24 && currentMinutes + modifiedMinutes >= 60)
				{
					h = (currentHour +modifiedHours +1)% 24;
					m = (modifiedMinutes + currentMinutes)%60;
				}
			}

			String m10;
			//in case minutes are less than 10
			if(m < 10){
				m10 = "0" + Integer.toString(m);
				answer.setText(h+":" + m10);
			}
			else{
				answer.setText(h+":" + m);
			}

		}
	}
}