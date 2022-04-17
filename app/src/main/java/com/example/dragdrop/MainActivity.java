package com.example.dragdrop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private RelativeLayout revativ;
    private static final String BUTTON_ETIKET="DRAG BUTTON";
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setTag(BUTTON_ETIKET);
        revativ = findViewById(R.id.relativ);

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                String [] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData clipData = new ClipData(v.getTag().toString(),mimeTypes,item);
                View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(clipData,dragShadowBuilder,v,0);

                v.setVisibility(v.INVISIBLE);
                return true;
            }
        });
        revativ.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        params =(RelativeLayout.LayoutParams)button.getLayoutParams();
                        Log.e("Sonuç","ACTION_DRAG_STARTED");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.e("Sonuç","ACTION_DRAG_ENTERED");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.e("Sonuç","ACTION_DRAG_EXITED");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.e("Sonuç","ACTION_DRAG_LOCATION");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.e("Sonuç","ACTION_DRAG_ENDED");
                        break;
                    case DragEvent.ACTION_DROP:
                        params.leftMargin = (int) event.getX();
                        params.rightMargin = (int) event.getY();

                        View gorselNesne = (View) event.getLocalState();
                        ViewGroup eskiTasarimAlani = (ViewGroup) gorselNesne.getParent();
                        eskiTasarimAlani.removeView(gorselNesne);

                        RelativeLayout hedefTasarimAlani = (RelativeLayout) v;

                        hedefTasarimAlani.addView(gorselNesne,params);
                        gorselNesne.setVisibility(v.VISIBLE);


                        Log.e("Sonuç","ACTION_DROP");
                        break;
                    default: break;
                }
                return true;
            }
        });

    }
}