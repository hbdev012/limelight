package limelight.os;

import limelight.Context;

public abstract class OS
{
  protected boolean inKioskMode;

  protected abstract void turnOnKioskMode();
  protected abstract void turnOffKioskMode();

  public void enterKioskMode()
  {
    if(inKioskMode)
      return;
    turnOnKioskMode();
    inKioskMode = true;
  }

  public void exitKioskMode()
  {
    inKioskMode = false;
    turnOffKioskMode();
  }

  public boolean isInKioskMode()
  {
    return inKioskMode;
  }

  public abstract void configureSystemProperties();

  public void appIsStarting()
  {
  }

  public void openProduction(String productionPath)
  {
    Context.instance().studio.open(productionPath);
  }
}