package com.chaosthedude.calculations.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;

public class GuiConvertNether extends GuiScreen {

	private GuiScreen parentScreen;

	private GuiTransparentNumberField overworldX;
	private GuiTransparentNumberField overworldY;
	private GuiTransparentNumberField overworldZ;

	private GuiTransparentNumberField netherX;
	private GuiTransparentNumberField netherY;
	private GuiTransparentNumberField netherZ;

	private GuiTransparentButton buttonBack;

	public GuiConvertNether(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
	}

	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);

		setupTextFields();
		setupButtons();
	}

	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button == buttonBack) {
				mc.displayGuiScreen(parentScreen);
			}
		}
	}

	@Override
	public void updateScreen() {
		overworldX.updateCursorCounter();
		overworldY.updateCursorCounter();
		overworldZ.updateCursorCounter();

		netherX.updateCursorCounter();
		netherY.updateCursorCounter();
		netherZ.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		overworldX.textboxKeyTyped(typedChar, keyCode);
		overworldY.textboxKeyTyped(typedChar, keyCode);
		overworldZ.textboxKeyTyped(typedChar, keyCode);

		netherX.textboxKeyTyped(typedChar, keyCode);
		netherY.textboxKeyTyped(typedChar, keyCode);
		netherZ.textboxKeyTyped(typedChar, keyCode);

		if (overworldX.isValid() && overworldX.isFocused()) {
			netherX.setText(String.valueOf(toNether(Integer.valueOf(overworldX.getText()))));
		}

		if (overworldY.isValid() && overworldY.isFocused()) {
			netherY.setText(String.valueOf((int) MathHelper.clamp(Math.floor(Integer.valueOf(overworldY.getText())), 0, 128)));
		}

		if (overworldZ.isValid() && overworldZ.isFocused()) {
			netherZ.setText(String.valueOf(toNether(Integer.valueOf(overworldZ.getText()))));
		}

		if (netherX.isValid() && netherX.isFocused()) {
			overworldX.setText(String.valueOf(toOverworld(Integer.valueOf(netherX.getText()))));
		}

		if (netherY.isValid() && netherY.isFocused()) {
			overworldY.setText(String.valueOf((int) MathHelper.clamp(Math.floor(Integer.valueOf(netherY.getText())), 0, 255)));
		}

		if (netherZ.isValid() && netherZ.isFocused()) {
			overworldZ.setText(String.valueOf(toOverworld(Integer.valueOf(netherZ.getText()))));
		}

		if (keyCode == Keyboard.KEY_ESCAPE) {
			mc.displayGuiScreen(parentScreen);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, I18n.format("calculations.convertNether"), width / 2, 15, 0xffffff);
		drawCenteredString(fontRendererObj, I18n.format("string.overworld"), width / 2, 55, 0xffffff);
		drawCenteredString(fontRendererObj, I18n.format("string.nether"), width / 2, 105, 0xffffff);

		overworldX.drawTextBox();
		overworldY.drawTextBox();
		overworldZ.drawTextBox();

		netherX.drawTextBox();
		netherY.drawTextBox();
		netherZ.drawTextBox();

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		overworldX.mouseClicked(mouseX, mouseY, mouseButton);
		overworldY.mouseClicked(mouseX, mouseY, mouseButton);
		overworldZ.mouseClicked(mouseX, mouseY, mouseButton);

		netherX.mouseClicked(mouseX, mouseY, mouseButton);
		netherY.mouseClicked(mouseX, mouseY, mouseButton);
		netherZ.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);

		overworldX.mouseReleased(mouseX, mouseY, state);
		overworldY.mouseReleased(mouseX, mouseY, state);
		overworldZ.mouseReleased(mouseX, mouseY, state);

		netherX.mouseReleased(mouseX, mouseY, state);
		netherY.mouseReleased(mouseX, mouseY, state);
		netherZ.mouseReleased(mouseX, mouseY, state);
	}

	private void setupButtons() {
		buttonList.clear();

		buttonBack = addButton(new GuiTransparentButton(0, 5, height - 25, 100, 20, I18n.format("string.back")));
	}

	private void setupTextFields() {
		overworldX = new GuiTransparentNumberField(0, fontRendererObj, width / 2 - 155, 70, 100, 20);
		overworldY = new GuiTransparentNumberField(1, fontRendererObj, width / 2 - 50, 70, 100, 20);
		overworldZ = new GuiTransparentNumberField(2, fontRendererObj, width / 2 + 55, 70, 100, 20);

		netherX = new GuiTransparentNumberField(3, fontRendererObj, width / 2 - 155, 120, 100, 20);
		netherY = new GuiTransparentNumberField(4, fontRendererObj, width / 2 - 50, 120, 100, 20);
		netherZ = new GuiTransparentNumberField(5, fontRendererObj, width / 2 + 55, 120, 100, 20);
	}

	private int toNether(double x) {
		return MathHelper.floor(x / 8);
	}

	private int toOverworld(double x) {
		return MathHelper.clamp(MathHelper.floor(x * 8), -29999872, 29999872);
	}

}
