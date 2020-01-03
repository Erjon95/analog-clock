package clock1;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.scene.effect.BlendMode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Clock1 extends Application{
	
	Line seconds_hand = new Line();
    Line minutes_hand = new Line();
    Line hours_hand = new Line ();
    Circle circle = new Circle();
    
    private void initialize ()
	{
		int hour = java.time.LocalTime.now().getHour();
		int minute = java.time.LocalTime.now().getMinute();
		int second = java.time.LocalTime.now().getSecond();
		
		double seconds_degree = second * 6;
		double minutes_degree = minute * 6;
		double hours_degree = hour * 30 + minute * .5;
		
		Rotate rotate_seconds = new Rotate (seconds_degree, 100.0f, 100.0f);
		Rotate rotate_minutes = new Rotate (minutes_degree, 100.0f, 100.0f);
		Rotate rotate_hours = new Rotate (hours_degree, 100.0f, 100.0f);
		
		seconds_hand.getTransforms().add(rotate_seconds);
		minutes_hand.getTransforms().add(rotate_minutes);
		hours_hand.getTransforms().add(rotate_hours);
	}
    
	class Operate extends TimerTask
	{
		
		public void run ()
		{
			
			Rotate rotate = new Rotate (6, 100.0f, 100.0f);
			Rotate rotate_hours = new Rotate (.5, 100.0f, 100.0f);
			seconds_hand.getTransforms().add(rotate);
			
			if (java.time.LocalTime.now().getSecond() == 59)
			{
				minutes_hand.getTransforms().add(rotate);
				hours_hand.getTransforms().add(rotate_hours);
			}
		}
	}

    @Override
    public void start(Stage stage) {
    	stage.setTitle("Clock");
        Group root = new Group();
        final Scene scene = new Scene(root, 200, 250);
        scene.setFill(null);
        
        Timer timer = new Timer (true);
        
        circle.setCenterX(100.0f);
        circle.setCenterY(100.0f);
        circle.setRadius(80.0f);
        circle.setStroke(Color.BLUE);
        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(2);
        
        seconds_hand.setStartX(100.0f);
        seconds_hand.setStartY(120.0f);
        seconds_hand.setEndX(100.0f);
        seconds_hand.setEndY(30.0f);
        seconds_hand.setStroke(Color.RED);
        seconds_hand.setBlendMode(BlendMode.SRC_OVER);
        
        minutes_hand.setStartX(100.0f);
        minutes_hand.setStartY(110.0f);
        minutes_hand.setEndX(100.0f);
        minutes_hand.setEndY(30.0f);
        minutes_hand.setStroke(Color.RED);
        minutes_hand.setStrokeWidth(3);
        minutes_hand.setBlendMode(BlendMode.SRC_OVER);
        
        hours_hand.setStartX(100.0f);
        hours_hand.setStartY(110.0f);
        hours_hand.setEndX(100.0f);
        hours_hand.setEndY(60.0f);
        hours_hand.setStroke(Color.RED);
        hours_hand.setStrokeWidth(3);
        hours_hand.setBlendMode(BlendMode.SRC_OVER);
        
        root.getChildren().add(circle);
        root.getChildren().add(seconds_hand);
        root.getChildren().add(minutes_hand);
        root.getChildren().add(hours_hand);
        
        initialize ();
        
        timer.scheduleAtFixedRate(new Operate (), 0, 1000);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

