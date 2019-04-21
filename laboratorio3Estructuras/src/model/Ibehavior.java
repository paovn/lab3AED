package model;

import java.sql.Date;
import java.util.List;

public interface Ibehavior {
	public double getAmountMax(Date dateStart, Date dateEnd);
	public double getAmountMin(Date dateStart, Date dateEnd);
	public Date getTimeMaxIncrease();
	public Date getTimeMinIncrease();
	public List<Double> getDatasToShowStatus();
	
	
}
