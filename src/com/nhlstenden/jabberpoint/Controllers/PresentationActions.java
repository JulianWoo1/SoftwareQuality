package com.nhlstenden.jabberpoint.Controllers;

public class PresentationActions implements PresentationActionsApi {

  private final PresentationService service;

  public PresentationActions(PresentationService service) {
    this.service = service;
  }

  @Override
  public void openPresentation() {
    service.open();
  }

  @Override
  public void newPresentation() {
    service.clear();
  }

  @Override
  public void savePresentation() {
    service.save();
  }

  @Override
  public void nextSlide() {
    service.next();
  }

  @Override
  public void previousSlide() {
    service.previous();
  }

  @Override
  public void gotoSlide() {
    service.goTo();
  }

  @Override
  public void showAbout() {
    service.about();
  }

  @Override
  public void exitApplication() {
    service.exit();
  }
}