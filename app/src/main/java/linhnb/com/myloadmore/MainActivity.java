package linhnb.com.myloadmore;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<ArrayList<Contact>> mContactMember;
    private List<Contact> mContacts;
    private int mSize;
    private List<Contact> contacts;
    private ContactAdapter contactAdapter;
    private RecyclerView mRecyclerView;
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContacts = Contact.initContact();
        mSize = mContacts.size();
        mContactMember = new ArrayListUtil<Contact>().chiaContactVaoTungarrray(mContacts, 20);
        contacts = new ArrayList<>();
        contacts.addAll(mContactMember.get(0));
        mRecyclerView = findViewById(R.id.recycler_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(mRecyclerView, contacts, this);
        mRecyclerView.setAdapter(contactAdapter);

        // set load more listener for the RecyclerView adapter
        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (contacts.size() < mSize) {
                    contacts.add(null);
                    contactAdapter.notifyItemInserted(contacts.size() - 1);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            contacts.remove(contacts.size() - 1);
                            contactAdapter.notifyItemRemoved(contacts.size());
                            //Generating more data
                            contacts.addAll(moreData());
                            contactAdapter.notifyDataSetChanged();
                            contactAdapter.setLoaded();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(MainActivity.this, "No item more", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<Contact> moreData() {
        current++;
        return mContactMember.get(current);
    }
}