/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml),
 * Marcelina Knitter (@marcelinkaaa)
 * Copyright (c) 2011-2016, FrostWire(R). All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.frostwire.android.gui.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.frostwire.android.R;
import com.frostwire.android.gui.views.AbstractDialog;
import com.frostwire.android.gui.views.ClickAdapter;

/**
 * @author gubatron
 * @author aldenml
 * @author marcelinkaaa
 */
public class YesNoDialog extends AbstractDialog {
    private static final String ID_KEY = "id";
    private static final String TITLE_KEY = "title";
    private static final String MESSAGE_KEY = "message";
    private Button buttonNo;
    private Button buttonYes;
    private String id;

    public YesNoDialog() {
        super(R.layout.dialog_default);
    }

    public static YesNoDialog newInstance(String id, int titleId, int messageId) {
        YesNoDialog f = new YesNoDialog();

        Bundle args = new Bundle();
        args.putString(ID_KEY, id);
        args.putInt(TITLE_KEY, titleId);
        args.putInt(MESSAGE_KEY, messageId);
        f.setArguments(args);

        return f;
    }

    @Override
    protected void initComponents(Dialog dlg, Bundle savedInstanceState) {
        Bundle args = getArguments();

        id = args.getString(ID_KEY);

        int titleId = args.getInt(TITLE_KEY);
        int messageId = args.getInt(MESSAGE_KEY);

        dlg.setContentView(R.layout.dialog_default);

        TextView defaultDialogTitle = findView(dlg, R.id.dialog_default_title);
        defaultDialogTitle.setText(titleId);

        TextView defaultDialogText = findView(dlg, R.id.dialog_default_text);
        defaultDialogText.setText(messageId);

        ButtonListener yesButtonListener = new ButtonListener(this, Dialog.BUTTON_POSITIVE);
        ButtonListener noButtonListener = new ButtonListener(this, Dialog.BUTTON_NEGATIVE);

        buttonYes = findView(dlg, R.id.dialog_default_button_yes);
        buttonYes.setText(android.R.string.yes);
        buttonYes.setOnClickListener(yesButtonListener);

        buttonNo = findView(dlg, R.id.dialog_default_button_no);
        buttonNo.setText(android.R.string.no);
        buttonNo.setOnClickListener(noButtonListener);
    }

    @Override
    protected void performDialogClick(String tag, int which) {
        super.performDialogClick(id, which);
    }

    private static final class ButtonListener extends ClickAdapter<YesNoDialog> {
        private int which;

        public ButtonListener(YesNoDialog owner, int which) {
            super(owner);
            this.which = which;
        }

        @Override
        public void onClick(YesNoDialog owner, View v) {
            super.onClick(owner, v);
            if (this.which == Dialog.BUTTON_POSITIVE) {
                owner.performDialogClick(this.which);
            }
            owner.dismiss();
        }
    }
}