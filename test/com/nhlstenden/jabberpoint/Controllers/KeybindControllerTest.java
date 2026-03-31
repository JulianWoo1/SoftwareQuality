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
        public void nextSlide() { nextCalled = true; }

        @Override
        public void previousSlide() { previousCalled = true; }

        @Override
        public void exitApplication() { exitCalled = true; }
    }

    private KeyEvent key(int keyCode)
    {
        return new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }

    @BeforeEach
    void setup()
    {
        actions = new FakeActions();
        controller = new KeybindController(actions);
    }

    @Test
    void keyPressed_PageDown_ShouldCallNextSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_PAGE_DOWN));
        assertTrue(actions.nextCalled);
    }

    @Test
    void keyPressed_Down_ShouldCallNextSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_DOWN));
        assertTrue(actions.nextCalled);
    }

    @Test
    void keyPressed_Enter_ShouldCallNextSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_ENTER));
        assertTrue(actions.nextCalled);
    }

    @Test
    void keyPressed_Plus_ShouldCallNextSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_PLUS));
        assertTrue(actions.nextCalled);
    }

    @Test
    void keyPressed_PageUp_ShouldCallPreviousSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_PAGE_UP));
        assertTrue(actions.previousCalled);
    }

    @Test
    void keyPressed_Up_ShouldCallPreviousSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_UP));
        assertTrue(actions.previousCalled);
    }

    @Test
    void keyPressed_Minus_ShouldCallPreviousSlide()
    {
        controller.keyPressed(key(KeyEvent.VK_MINUS));
        assertTrue(actions.previousCalled);
    }

    @Test
    void keyPressed_Q_ShouldCallExitApplication()
    {
        controller.keyPressed(key(KeyEvent.VK_Q));
        assertTrue(actions.exitCalled);
    }

    @Test
    void keyPressed_UnknownKey_ShouldDoNothing()
    {
        controller.keyPressed(key(KeyEvent.VK_A));
        assertFalse(actions.nextCalled);
        assertFalse(actions.previousCalled);
        assertFalse(actions.exitCalled);
    }
}