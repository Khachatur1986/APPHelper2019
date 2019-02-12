package com.example.apphelper2019.ex.db_ex.todo_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apphelper2019.R;
import com.example.apphelper2019.ex.db_ex.todo_ex.model.ToDo;

import java.util.List;

public class Main9Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNewToDoString, editTextToDoId, editTextNewToDo, editTextModifyToDoId;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    private TodoDB todoDB;
    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        initViews();

        todoDB = TodoDB.getInstance(this);
        toDos = todoDB.getAllToDos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNewList();
    }

    private void initViews() {
        editTextNewToDoString = findViewById(R.id.editTextNewToDoString);
        editTextToDoId = findViewById(R.id.editTextToDoId);
        editTextNewToDo = findViewById(R.id.editTextNewToDo);
        editTextModifyToDoId = findViewById(R.id.editTextModifyToDoId);

        textViewToDos = findViewById(R.id.textViewToDos);

        buttonAddToDo = findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo = findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = findViewById(R.id.buttonModifyToDo);

        buttonModifyToDo.setOnClickListener(this);
        buttonRemoveToDo.setOnClickListener(this);
        buttonAddToDo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddToDo:
                addNewToDo();
                break;
            case R.id.buttonRemoveToDo:
                removeToDo();
                break;
            case R.id.buttonModifyToDo:
                modifyToDo();
                break;
            default:
                break;
        }
    }

    private void setNewList() {
        textViewToDos.setText(getToDoListString());
    }

    private void addNewToDo() {
        todoDB.insert(editTextNewToDoString.getText().toString());
        setNewList();
    }

    private void removeToDo() {
        todoDB.delete(Integer.parseInt(editTextToDoId.getText().toString()));
        setNewList();
    }

    private void modifyToDo() {
        int id = Integer.parseInt(editTextModifyToDoId.getText().toString());
        String newToDO = editTextNewToDo.getText().toString();
        todoDB.modify(id, newToDO);
        setNewList();
    }


    private String getToDoListString() {
        toDos = todoDB.getAllToDos();
        if (toDos != null && toDos.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (ToDo toDo : toDos) {
                stringBuilder.append(toDo.getId() + ", " + toDo.getToDo() + "\n");
            }
            return stringBuilder.toString();
        } else {
            return "No todo items";
        }
    }
}
