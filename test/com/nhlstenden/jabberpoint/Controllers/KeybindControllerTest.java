package com.nhlstenden.jabberpoint.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class KeybindControllerTest
{
    private FakeActions actions;
    private KeybindController controller;

    static class FakeActions extends PresentationActions
    {
        boolean nextCalled = false;
        boolean previousCalled = false;
        boolean exitCalled = false;

        FakeActions()
        {
            super(null, null);
        }

        @Override
        public void nextSlide() {
            nextCalled = true;
        }

        @Override
        public void previousSlide() {
            previousCalled = true;
        }

        @Override
        public void exitApplication() {
            exitCalled = true;
        }
    }

    @BeforeEach
    void setup()
    {
        actions = new FakeActions();
        controller = new KeybindController(actions);
    }

    @Test
    void keyPressed_DownKey_ShouldCallNextSlide()
    {
        controller.keyPressed(new KeyEvent(new Button(), 1, 1, 0, KeyEvent.VK_DOWN));

        assertTrue(actions.nextCalled);
    }

    @Test
    void keyPressed_UpKey_ShouldCallPreviousSlide()
    {
        controller.keyPressed(new KeyEvent(new Button(), 1, 1, 0, KeyEvent.VK_UP));

        assertTrue(actions.previousCalled);
    }

    @Test
    void keyPressed_QKey_ShouldCallExitApplication()
    {
        controller.keyPressed(new KeyEvent(new Button(), 1, 1, 0, KeyEvent.VK_Q));

        assertTrue(actions.exitCalled);
    }

    @Test
    void keyPressed_UnknownKey_ShouldDoNothing()
    {
        controller.keyPressed(new KeyEvent(new Button(), 1, 1, 0, KeyEvent.VK_A));

        assertFalse(actions.nextCalled);
        assertFalse(actions.previousCalled);
        assertFalse(actions.exitCalled);
    }
}