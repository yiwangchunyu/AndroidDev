package com.example.springrain.ch7project1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnClickListener {

    private ListView listView;
    private ItemAdapter adapter;
    private List<Item> itemList = new ArrayList<Item>();
    private AlertDialog dialog;
    private AlertDialog updateDialog;
    private AlertDialog insertDialog;
    private AlertDialog queryDialog;
    private Item onLongClickItem;
    private SQLiteDatabase db;
    private StringBuilder sb;
    private MyDBOpenHelper myDBOpenHelper;
    private Item tempItem;
    private EditText nameEd;
    private EditText priceEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempItem=new Item(0,"",0);
        listView = (ListView) findViewById(R.id.listView);
        myDBOpenHelper = new MyDBOpenHelper(this,"my.db",null,1);
        db = myDBOpenHelper.getWritableDatabase();
        myDBOpenHelper.onCreate(db);
        init(db);
        onLoad();

    }

    protected void onLoad(){
        itemList.clear();
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                itemList.add(new Item(id,name,price));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
    }
    private void init(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("name","iphone");
        values.put("price",3000);
        this.db.insert("product", null, values);
        values.put("name","htc");
        values.put("price",3500);
        this.db.insert("product", null, values);
        values.put("name","moto");
        values.put("price",4000);
        this.db.insert("product", null, values);
        values.put("name","sangsung");
        values.put("price",4500);
        this.db.insert("product", null, values);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.onLongClickItem = (Item) this.adapter.getItem(i);
        this.setAlertDialog(this.onLongClickItem);
        return false;
    }

    private void setAlertDialog(Item view) {
        LayoutInflater factory = LayoutInflater.from(getApplicationContext());
        View dialogView = factory.inflate(R.layout.dialog, null);
        final TextView delete = (TextView) dialogView.findViewById(R.id.delete);
        final TextView modify = (TextView) dialogView.findViewById(R.id.modify);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
        dialog = new AlertDialog.Builder(MainActivity.this).setView(dialogView).create();
        dialog.show();
    }


    private void setUpdateDialog() {
        LayoutInflater factory = LayoutInflater.from(getApplicationContext());
        View updateDialogView = factory.inflate(R.layout.update_dialog, null);
        Button setButton = (Button) updateDialogView.findViewById(R.id.set);
        setButton.setOnClickListener(this);
        Button calcelButton = (Button) updateDialogView.findViewById(R.id.cancel);
        calcelButton.setOnClickListener(this);
        this.nameEd = (EditText) updateDialogView.findViewById(R.id.update_name);
        this.priceEd = (EditText) updateDialogView.findViewById(R.id.update_price);
        updateDialog = new AlertDialog.Builder(MainActivity.this).setView(updateDialogView).create();
        updateDialog.show();
    }

    private void setInsertDialog() {
        LayoutInflater factory = LayoutInflater.from(getApplicationContext());
        View insertDialogView = factory.inflate(R.layout.insert_dialog, null);
        Button setButton = (Button) insertDialogView.findViewById(R.id.set_insert);
        setButton.setOnClickListener(this);
        Button calcelButton = (Button) insertDialogView.findViewById(R.id.cancel_insert);
        calcelButton.setOnClickListener(this);
        this.nameEd = (EditText) insertDialogView.findViewById(R.id.update_name);
        this.priceEd = (EditText) insertDialogView.findViewById(R.id.update_price);
        insertDialog = new AlertDialog.Builder(MainActivity.this).setView(insertDialogView).create();
        insertDialog.show();
    }

    private void setQueryDialog() {
        LayoutInflater factory = LayoutInflater.from(getApplicationContext());
        View queryDialogView = factory.inflate(R.layout.query_dialog, null);
        Button setButton = (Button) queryDialogView.findViewById(R.id.set_query);
        setButton.setOnClickListener(this);
        Button calcelButton = (Button) queryDialogView.findViewById(R.id.cancel_query);
        calcelButton.setOnClickListener(this);
        this.nameEd = (EditText) queryDialogView.findViewById(R.id.update_name);
        queryDialog = new AlertDialog.Builder(MainActivity.this).setView(queryDialogView).create();
        queryDialog.show();
    }

    @Override
    public void onClick(View view) {
        String query;
        switch (view.getId()){
            case R.id.delete:
                db.delete("product","id=?",new String[]{String.valueOf(this.onLongClickItem.getId())});
//                this.adapter.remove(this.onLongClickItem);
                this.onLoad();
                this.dialog.hide();
                break;
            case R.id.modify:
                this.dialog.hide();
                setUpdateDialog();
                break;

            case R.id.set:
                query = "update product set price="+this.priceEd.getText().toString()+" where name='"+this.nameEd.getText().toString()+"'";
                db.execSQL(query);
                this.onLoad();
                this.updateDialog.hide();
                break;
            case R.id.cancel:
                this.updateDialog.hide();
                break;
            case R.id.set_insert:
                query = "insert into product(name, price) values('"+this.nameEd.getText().toString()+"',"+this.priceEd.getText().toString()+")";
                db.execSQL(query);
                Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT);
                this.onLoad();
                this.insertDialog.hide();
                break;
            case R.id.cancel_insert:
                this.insertDialog.hide();
                break;
            case R.id.set_query:
                itemList.clear();
                Cursor cursor;
                if(this.nameEd.getText().toString().length()<=0){
                    cursor = db.query("product", null, null, null, null, null, null);

                }else{
                    cursor = db.query("product", null, "name=?", new String[]{this.nameEd.getText().toString()}, null, null, null);

                }
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int price = cursor.getInt(cursor.getColumnIndex("price"));
                        itemList.add(new Item(id,name,price));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList);
                listView.setAdapter(adapter);
                listView.setOnItemLongClickListener(this);
                this.queryDialog.hide();
                break;
            case R.id.cancel_query:
                this.queryDialog.hide();
                break;
            default:
                this.dialog.hide();
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(1,1,1,"增加");
        menu.add(1,2,2,"查询");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        String query;
        switch (id){
            case 1:
                setInsertDialog();
                break;
            case 2:
                setQueryDialog();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
