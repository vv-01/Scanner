package com.asv.codescan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.MultiFormatReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class OpenCam extends AppCompatActivity {
    CameraSource cs;
    BarcodeDetector bd;
    SurfaceView sv;
    ToneGenerator tg;
    String barcodeData;
    TextView barcodeText;
    Bundle bundle = new Bundle();
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cam);
        sv = findViewById(R.id.surfaceView2);
        barcodeText = findViewById(R.id.textView4);
        tg = new ToneGenerator(AudioManager.STREAM_MUSIC,100);
        StartCamera();
    }
    public void StartCamera() {
        bd = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        cs = new CameraSource.Builder(this,bd).setRequestedPreviewSize(972,1208).setAutoFocusEnabled(true).build();
        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

                try {
                    if (ActivityCompat.checkSelfPermission(OpenCam.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cs.start(sv.getHolder());
                    }
                    else {
                        ActivityCompat.requestPermissions(OpenCam.this, new
                                String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }
                } catch(IOException e){
                    throw new RuntimeException(e);
                }

            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                cs.stop();
            }

        });
        bd.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(OpenCam.this, "Scanner stops", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {


                    barcodeText.post(new Runnable() {

                        @Override
                        public void run() {

                            if (barcodes.valueAt(0).email != null) {
                                barcodeText.removeCallbacks(null);
                                barcodeData = barcodes.valueAt(0).email.address;
                                //barcodeText.setText(barcodeData);
                                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150);

                            } else {

                                barcodeData = barcodes.valueAt(0).displayValue;
                                //barcodeText.setText(barcodeData);
                                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150);

                            }
                            //JSONObject stringConvert = null;
//                            try {
//                                stringConvert = new JSONObject(barcodeData);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
                           //System.out.println(barcodeData.getClass());
                            //System.out.println(barcodeString);
                            bundle.putString("DATA",barcodeData);

                            Intent intent  = new Intent(OpenCam.this,Api.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        //getSupportActionBar().hide();
        cs.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //getSupportActionBar().hide();
        StartCamera();
    }
}