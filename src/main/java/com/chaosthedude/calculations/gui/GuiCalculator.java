package com.chaosthedude.calculations.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiCalculator extends GuiScreen {

	private GuiScreen parentScreen;

	private GuiTransparentButton buttonAllClear;
	private GuiTransparentButton buttonBackspace;

	private GuiTransparentButton buttonModulus;
	private GuiTransparentButton buttonDivide;
	private GuiTransparentButton buttonMultiply;
	private GuiTransparentButton buttonSubtract;
	private GuiTransparentButton buttonAdd;
	private GuiTransparentButton buttonEquals;

	private GuiTransparentButton button0;
	private GuiTransparentButton button1;
	private GuiTransparentButton button2;
	private GuiTransparentButton button3;
	private GuiTransparentButton button4;
	private GuiTransparentButton button5;
	private GuiTransparentButton button6;
	private GuiTransparentButton button7;
	private GuiTransparentButton button8;
	private GuiTransparentButton button9;

	private GuiTransparentButton buttonNegate;
	private GuiTransparentButton buttonPeriod;

	private GuiTransparentButton buttonDistance;
	private GuiTransparentButton buttonConvertNether;

	private GuiTransparentButton buttonBack;

	private GuiTerminal terminal;

	private int buttonID;

	public GuiCalculator(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;

		buttonID = -1;
	}

	@Override
	public void initGui() {
		setupButtons();

		terminal = new GuiTerminal(fontRenderer, width / 2 - 46, 46, 92, 20);
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id >= 0) {
				terminal.insertTerm(button.id);
			} else if (button == buttonAllClear) {
				terminal.clear();
			} else if (button == buttonBackspace) {
				terminal.back();
			} else if (button == buttonModulus) {
				terminal.remainder();
			} else if (button == buttonDivide) {
				terminal.divide();
			} else if (button == buttonMultiply) {
				terminal.multiply();
			} else if (button == buttonAdd) {
				terminal.add();
			} else if (button == buttonSubtract) {
				terminal.subtract();
			} else if (button == buttonEquals) {
				terminal.evaluate();
			} else if (button == buttonNegate) {
				terminal.negateTerm();
			} else if (button == buttonPeriod) {
				terminal.insertPeriod();
			} else if (button == buttonConvertNether) {
				mc.displayGuiScreen(new GuiConvertNether(mc.currentScreen));
			} else if (button == buttonDistance) {
				mc.displayGuiScreen(new GuiCalculateDistance(mc.currentScreen));
			} else if (button == buttonBack) {
				mc.displayGuiScreen(parentScreen);
			}
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		terminal.keyTyped(typedChar, keyCode);
		if (keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(null);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, I18n.format("calculations.calculator"), width / 2, 15, 0xffffff);
		terminal.drawScreen(mouseX, mouseY, partialTicks);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected <T extends GuiButton> T addButton(T button) {
		buttonList.add(button);
		buttonID--;
		return button;
	}

	private void setupButtons() {
		buttonList.clear();

		buttonAllClear = addButton(new GuiTransparentButton(buttonID, width / 2 - 46, 70, 20, 20, "AC"));
		buttonModulus = addButton(new GuiTransparentButton(buttonID, width / 2 - 22, 70, 20, 20, "%"));
		buttonBackspace = addButton(new GuiTransparentButton(buttonID, width / 2 + 2, 70, 20, 20, "<"));
		buttonDivide = addButton(new GuiTransparentButton(buttonID, width / 2 + 26, 70, 20, 20, "\u00f7"));
		button7 = addButton(new GuiTransparentButton(7, width / 2 - 46, 94, 20, 20, "7"));
		button8 = addButton(new GuiTransparentButton(8, width / 2 - 22, 94, 20, 20, "8"));
		button9 = addButton(new GuiTransparentButton(9, width / 2 + 2, 94, 20, 20, "9"));
		buttonMultiply = addButton(new GuiTransparentButton(-5, width / 2 + 26, 94, 20, 20, "x"));
		button4 = addButton(new GuiTransparentButton(4, width / 2 - 46, 118, 20, 20, "4"));
		button5 = addButton(new GuiTransparentButton(5, width / 2 - 22, 118, 20, 20, "5"));
		button6 = addButton(new GuiTransparentButton(6, width / 2 + 2, 118, 20, 20, "6"));
		buttonSubtract = addButton(new GuiTransparentButton(buttonID, width / 2 + 26, 118, 20, 20, "-"));
		button1 = addButton(new GuiTransparentButton(1, width / 2 - 46, 142, 20, 20, "1"));
		button2 = addButton(new GuiTransparentButton(2, width / 2 - 22, 142, 20, 20, "2"));
		button3 = addButton(new GuiTransparentButton(3, width / 2 + 2, 142, 20, 20, "3"));
		buttonAdd = addButton(new GuiTransparentButton(buttonID, width / 2 + 26, 142, 20, 20, "+"));
		buttonNegate = addButton(new GuiTransparentButton(buttonID, width / 2 - 46, 166, 20, 20, "\u00b1"));
		button0 = addButton(new GuiTransparentButton(0, width / 2 - 22, 166, 20, 20, "0"));
		buttonPeriod = addButton(new GuiTransparentButton(buttonID, width / 2 + 2, 166, 20, 20, "."));
		buttonEquals = addButton(new GuiTransparentButton(buttonID, width / 2 + 26, 166, 20, 20, "="));
		buttonDistance = addButton(new GuiTransparentButton(buttonID, width - 125, height - 50, 120, 20, I18n.format("calculations.calculateDistance")));
		buttonConvertNether = addButton(new GuiTransparentButton(buttonID, width - 125, height - 25, 120, 20, I18n.format("calculations.convertNether")));
		buttonBack = addButton(new GuiTransparentButton(buttonID, 5, height - 25, 100, 20, I18n.format("string.back")));
	}

}
