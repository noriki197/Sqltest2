package englishvocabulary.gameloft.com.sqltest;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {
    public static final String TABLE_NAME = "sinh_vien";


    int vitri=0,id;
    ListView listView;
    String cvMonDay,tgMonDay,ddMonDay;
    ArrayList<CongViec> congViecArrayList;
    CongViecAdapter arrayAdapter;
    Button btnadd,btnview,btncapnhat;
    Dialog dialog_themcongviec,dialog_capnhat;
    Button btnAdd_Dialog_ThemCV,btnHuy_Dialog_ThemCV;
    EditText edtThemCV_Dialog_ThemCV,edtThemTG_Dialog_ThemCV,edtThemDD_Dialog_ThemCV;
    EditText edtThemCV_Dialog_CapNhat,edtThemTG_Dialog_CapNhat,edtThemDD_Dialog_CapNhat;
    MyDBHelper mDB;
    SQLiteDatabase sqlDB;
    MyDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.lvtest);
        congViecArrayList = new ArrayList<>();
        arrayAdapter = new CongViecAdapter(MainActivity.this,R.layout.dong_listview,congViecArrayList);

        dialog_themcongviec = new Dialog(MainActivity.this);
        dialog_themcongviec.setContentView(R.layout.dialog_themcongviec);
        dialog_themcongviec.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog_capnhat = new Dialog(MainActivity.this);
        dialog_capnhat.setContentView(R.layout.dialog_capnhat);
        dialog_capnhat.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btnAdd_Dialog_ThemCV = (Button) dialog_themcongviec.findViewById(R.id.btnthem_Dialog_THEMCONGVIEC);
        btnHuy_Dialog_ThemCV = (Button) dialog_themcongviec.findViewById(R.id.btnhuy_Dialog_THEMCONGVIEC);
        btncapnhat = (Button) dialog_capnhat.findViewById(R.id.btncapnhat_Dialog_CNCONGVIEC);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnview = (Button) findViewById(R.id.btnview);

        edtThemCV_Dialog_ThemCV = (EditText) dialog_themcongviec.findViewById(R.id.edtthemcongviec_Dialog_THEMCONGVIEC);
        edtThemTG_Dialog_ThemCV = (EditText) dialog_themcongviec.findViewById(R.id.edtthemthoigian_Dialog_THEMCONGVIEC);
        edtThemDD_Dialog_ThemCV = (EditText) dialog_themcongviec.findViewById(R.id.edtthemdiadiem_Dialog_THEMCONGVIEC);

        edtThemCV_Dialog_CapNhat = (EditText) dialog_capnhat.findViewById(R.id.edtcapnhatcongviec_Dialog_CNCONGVIEC);
        edtThemTG_Dialog_CapNhat = (EditText) dialog_capnhat.findViewById(R.id.edtcapnhatthoigian_Dialog_CNCONGVIEC);
        edtThemDD_Dialog_CapNhat = (EditText) dialog_capnhat.findViewById(R.id.edtcapnhatdiadiem_Dialog_CNCONGVIEC);

        db = new MyDBHelper(this,"quanlycongviec",null,1);
        db.querydata("CREATE TABLE IF NOT EXISTS Monday(id INTEGER PRIMARY KEY,congviec VARCHAR,thoigian VARCHAR,diadiem VARCHAR)");

        Cursor cursor = db.getdata("SELECT * FROM Monday");
        congViecArrayList.clear();

       /* for(int i=0;i<2;i++)
        {
            db.querydata("DELETE FROM Monday WHERE id = '"+vitri+"'");
            vitri++;
        }*/

        while(cursor.moveToNext())
        {
            cvMonDay = cursor.getString(1);
            tgMonDay = cursor.getString(2);
            ddMonDay = cursor.getString(3);
            id = cursor.getInt(0);
            congViecArrayList.add(new CongViec(id,cvMonDay,tgMonDay,ddMonDay));
            arrayAdapter.notifyDataSetChanged();
            listView.setAdapter(arrayAdapter);
            vitri=id;
            vitri++;
        }
            //db.querydata("INSERT INTO viec VALUES('dichoi','5h','bar')");




        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                vitri=position;
                dialog_capnhat.show();
                return false;
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_themcongviec.show();
            }
        });

        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = String.valueOf(vitri);
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            cvMonDay = edtThemCV_Dialog_CapNhat.getText().toString();
            tgMonDay = edtThemTG_Dialog_CapNhat.getText().toString();
            ddMonDay = edtThemDD_Dialog_CapNhat.getText().toString();
            db.querydata("UPDATE Monday SET congviec='"+cvMonDay+"',thoigian = '"+tgMonDay+"',diadiem = '"+ddMonDay+"'WHERE id = '"+vitri+"'");
            congViecArrayList.set(vitri,new CongViec(vitri,cvMonDay,tgMonDay,ddMonDay));
            arrayAdapter.notifyDataSetChanged();
            listView.setAdapter(arrayAdapter);
            }
        });




      /*  cvMonDay = "Monday";
        tgMonDay = "thoigianMD";
        ddMonDay = "diadiemMD";
        db.querydata("INSERT INTO viec VALUE(cvMonDay,tgMonDay,ddMonDay)");*/

        btnAdd_Dialog_ThemCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cvMonDay = edtThemCV_Dialog_ThemCV.getText().toString();
                tgMonDay = edtThemTG_Dialog_ThemCV.getText().toString();
                ddMonDay = edtThemDD_Dialog_ThemCV.getText().toString();
                congViecArrayList.add(vitri,new CongViec(vitri,cvMonDay,tgMonDay,ddMonDay));
                arrayAdapter.notifyDataSetChanged();
                listView.setAdapter(arrayAdapter);

                db.querydata("INSERT INTO Monday VALUES('"+vitri+"','"+cvMonDay+"','"+tgMonDay+"','"+ddMonDay+"')");

                edtThemCV_Dialog_ThemCV.setText("");
                edtThemTG_Dialog_ThemCV.setText("");
                edtThemDD_Dialog_ThemCV.setText("");
                vitri++;
                dialog_themcongviec.dismiss();
            }



        });
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
    }
}
