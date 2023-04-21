package com.ensea.nya.graphics.gamesStates.animationEditor;

import static com.ensea.nya.commands.ConsoleCommands.getAnimationEditorState;

import java.util.ArrayList;

public class Animation extends ArrayList<Frame> {

	private static final long serialVersionUID = -6446948535636214405L;

	private Frame currentFrame;
	boolean haveFrame;

	private boolean paused;
	private int time, totalTime, index;

	public Animation() {
		super();
		time = 0;
		index = 0;
		totalTime = 0;
	}

	public void render() {
		haveFrame = size() > 0;
		if (haveFrame) {
			if (time >= currentFrame.getDuration()) {
				time = 0;
				nextFrame();
			} else
				currentFrame.draw();
		}
	}

	public void next() {
		nextFrame();
		time = 0;
	}

	private void nextFrame() {
		if ((currentFrame = getFrame(++index)) == null) {
			index = 0;
			totalTime = 0;
			currentFrame = getFrame(index);
		}
	}

	public void changeIndexOf(Frame frame, int index) {
		super.remove(index);
		add(index, frame);
	}

	@Override
	public Frame remove(int index) {
		Frame output = super.remove(index);
		recalculIndex();
		if (size() == 0)
			haveFrame = false;
		return output;
	}

	@Override
	public void add(int index, Frame frame) {
		super.add(index, frame);
		recalculIndex();
	}

	private void recalculIndex() {
		int count = 0;
		for (Frame frame : this) { frame.index = count; count++; }
	}

	public Frame getFrame(int index) {
		if (index < size() && index >= 0)
			return get(index);
		else
			return null;
	}

	public int getCurrentFrameIndex() {
		return index;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public int getTime() {
		return time;
	}

	public Frame getCurrentFrame() {
		return currentFrame;
	}

	public boolean isPaused() {
		return paused;
	}

	public void previous() {
		if ((currentFrame = getFrame(--index)) == null) {
			index = 0;
			totalTime = 0;
			currentFrame = getFrame(index);
		}
	}

	public void setPause(boolean pause) {
		this.paused = pause;
	}

	public boolean add(Frame frame) {
		if (!haveFrame)
			currentFrame = frame;
		frame.index = size();
		getAnimationEditorState().frameInfo.setFrame(frame);
		return super.add(frame);
	}

	public void setDeltatime(int delta) {
		if (haveFrame && !paused) {
			this.time += delta;
			totalTime += delta;
		}
	}
}
