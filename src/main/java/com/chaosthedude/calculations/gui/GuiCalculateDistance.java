package com.chaosthedude.calculations.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;

public class GuiCalculateDistance extends GuiScreen {

	private GuiTransparentNumberField xTextField;
	private GuiTransparentNumberField yTextField;
	private GuiTransparentNumberField zTextField;

	private int distance;

	private GuiScreen parentScreen;

	private GuiTransparentButton buttonBack;

	public GuiCalculateDistance(GuiScreen parentScreen) {
		this.parentScreen = parentScreen;
		distance = 0;
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
		xTextField.updateCursorCounter();
		yTextField.updateCursorCounter();
		zTextField.updateCursorCounter();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		xTextField.textboxKeyTyped(typedChar, keyCode);
		yTextField.textboxKeyTyped(typedChar, keyCode);
		zTextField.textboxKeyTyped(typedChar, keyCode);

		final int x = xTextField.isValid() ? Integer.valueOf(xTextField.getText()) : (int) mc.thePlayer.posX;
		final int y = yTextField.isValid() ? Integer.valueOf(yTextField.getText()) : (int) mc.thePlayer.posY;
		final int z = zTextField.isValid() ? Integer.valueOf(zTextField.getText()) : (int) mc.thePlayer.posZ;

		final double xDelta = mc.thePlayer.posX - x;
		final double yDelta = mc.thePlayer.posY - y;
		final double zDelta = mc.thePlayer.posZ - z;

		distance = (int) Math.sqrt(xDelta * xDelta + yDelta * yDelta + zDelta * zDelta);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();

		drawCenteredString(fontRendererObj, I18n.format("calculations.calculateDistance"), width / 2, 15, 0xffffff);

		xTextField.drawTextBox();
		yTextField.drawTextBox();
		zTextField.drawTextBox();

		drawCenteredString(fontRendererObj, I18n.format("string.distance") + ": " + String.valueOf(distance), width / 2, 100, 0xffffff);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		xTextField.mouseClicked(mouseX, mouseY, mouseButton);
		yTextField.mouseClicked(mouseX, mouseY, mouseButton);
		zTextField.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);

		xTextField.mouseReleased(mouseX, mouseY, state);
		yTextField.mouseReleased(mouseX, mouseY, state);
		zTextField.mouseReleased(mouseX, mouseY, state);
	}

	private void setupButtons() {
		buttonList.clear();

		buttonBack = addButton(new GuiTransparentButton(0, 5, height - 25, 100, 20, I18n.format("string.back")));
	}

	private void setupTextFields() {
		xTextField = new GuiTransparentNumberField(0, fontRendererObj, width / 2 - 155, 70, 100, 20);
		yTextField = new GuiTransparentNumberField(1, fontRendererObj, width / 2 - 50, 70, 100, 20);
		zTextField = new GuiTransparentNumberField(2, fontRendererObj, width / 2 + 55, 70, 100, 20);
	}

}