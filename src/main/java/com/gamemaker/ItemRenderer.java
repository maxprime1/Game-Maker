package com.gamemaker;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.gamemaker.model.ShapesModel;

public class ItemRenderer extends BasicComboBoxRenderer {
	
	@Override
	public Component getListCellRendererComponent(
			JList list, 
			Object value,
			int index, 
			boolean isSelected, 
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    ShapesModel item = (ShapesModel) value;

	    if (index == -1) {
	      setText(item.getName());
	      setIcon(null);
	    } else {
	      setText(item.getName());
	      setIcon(item.getImage());
	    }
	    return this;
	  }
	}