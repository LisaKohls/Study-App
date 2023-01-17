package de.hdmstuttgart.meinprojekt.view.todo;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import de.hdmstuttgart.meinprojekt.R;
import de.hdmstuttgart.meinprojekt.model.Converter;
import de.hdmstuttgart.meinprojekt.model.ToDoItem;
import de.hdmstuttgart.meinprojekt.viewmodel.ToDoViewModel;

public class DialogAdd {

    private final String title = "Title of your new To Do";
    private final String description = "Description: ";
    private final String dialogclosed = "onClick: closing dialog";
    private final String input = "onClick: capturing input";
    private final String tag = "DialogAdd";
    /**
     * This class is used when the users wants to add a new To do to the list
     * You can add title, a description and add or close dialog
     */

    private String inputTitle = "";
    private String inputTopic = "";
    private String currentTime;
    private Date time;
    private final View v;
    private View dialogView;
    private final ToDoViewModel viewModel;
    private ToDoItem toDoItem;
    private final AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText titleInput;
    private EditText topicInput;
    private Button btnCancel;
    private Button btnAdd;
    private TextView titleHeading;
    private TextView topicHeading;
    private final String errorMessage = "Please enter a valid To Do!";
    private Toast toastMessage;


    public DialogAdd(View v, AlertDialog.Builder dialogBuilder, ToDoViewModel viewModel) {
        this.v = v;
        this.dialogBuilder = dialogBuilder;
        this.viewModel = viewModel;
    }

    public void dialog() {

        dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.addtodo_dialog, null);

        btnCancel = dialogView.findViewById(R.id.btncancel);
        btnAdd = dialogView.findViewById(R.id.btnadd);

        titleInput = dialogView.findViewById(R.id.titleinput);
        topicInput = dialogView.findViewById(R.id.textInputEditText);

        titleHeading = dialogView.findViewById(R.id.titleheading);
        topicHeading = dialogView.findViewById(R.id.topicheading);

        titleHeading.setText(title);
        topicHeading.setText(description);

        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);

        dialog = dialogBuilder.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnCancel.setOnClickListener(
                a -> {
                    Log.d(tag, dialogclosed);
                    dialog.dismiss();
                });

        //if add button is clicked, title, description and the current date will be saved in the viewmodel
        btnAdd.setOnClickListener(
                a -> {
                    time = Calendar.getInstance().getTime();
                    currentTime = Converter.dateToTimestamp(time);

                    Log.d(tag, input);

                    inputTitle = titleInput.getText().toString();
                    inputTopic = topicInput.getText().toString();

                    this.toDoItem = new ToDoItem(inputTitle, currentTime, inputTopic, 0);

                    if (toDoItem.getTitle().equals("")) {
                        //if input has no title toast message will pop up
                        toastMessage = Toast.makeText(v.getContext(), errorMessage, Toast.LENGTH_LONG);
                        toastMessage.show();
                    } else {
                        //saving input in viewmodel
                        //ToDoItem savedToDo = attach(inputTitle, currentTime, inputTopic, 0);
                        viewModel.saveToDo(toDoItem);
                        dialog.dismiss();
                    }
                });
    }
    /*
    private ToDoItem attach(String title, String time, String topic, int status){
        ToDoItem savedItem = new ToDoItem(title, time, topic, status);
        return savedItem;
    }*/

}

