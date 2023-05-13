package com.asv.codescan;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn ;
    Intent intent;
    TextView textView;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        btn = findViewById(R.id.button);
        //sv = findViewById(R.id.surfaceView);
        //barcodeText = findViewById(R.id.textView3);
        //tg = new ToneGenerator(AudioManager.STREAM_MUSIC,100);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"trial",Toast.LENGTH_SHORT).show();
                //StartCamera();
                intent = new Intent(MainActivity.this,OpenCam.class);
                startActivity(intent);
            }

        });
    }

//    public void StartCamera() {
//        bd = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
//        cs = new CameraSource.Builder(this,bd).setRequestedPreviewSize(776,280).setAutoFocusEnabled(true).build();
//        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//
//                try {
//                    if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cs.start(sv.getHolder());
//                    }
//                    else {
//                            ActivityCompat.requestPermissions(MainActivity.this, new
//                                    String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
//                    }
//                    } catch(IOException e){
//                    throw new RuntimeException(e);
//                }
//
//            }
//            @Override
//            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
//                cs.stop();
//            }
//
//        });
//        bd.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//                Toast.makeText(MainActivity.this, "Scanner stops", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                if (barcodes.size() != 0) {
//
//
//                    barcodeText.post(new Runnable() {
//
//                        @Override
//                        public void run() {
//
//                            if (barcodes.valueAt(0).email != null) {
//                                barcodeText.removeCallbacks(null);
//                                barcodeData = barcodes.valueAt(0).email.address;
//                                barcodeText.setText(barcodeData);
//                                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
//                            } else {
//
//                                barcodeData = barcodes.valueAt(0).displayValue;
//                                barcodeText.setText(barcodeData);
//                                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
//
//                            }
//                        }
//                    });
//
//                }
//       }
//        });
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //getSupportActionBar().hide();
//        cs.release();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //getSupportActionBar().hide();
//        StartCamera();
//    }
}