package isomora.com.plantDetector

import android.os.Bundle
import android.widget.Toast
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Build
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import kotlinx.android.synthetic.main.activity_main.*;
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var mClassifier: Classifier
    private lateinit var mBitmap: Bitmap

    private val mCameraRequestCode = 0
    private val mGalleryRequestCode = 2

    private val mInputSize = 224
    private val mModelPath = "plant_disease_model.tflite"
    private val mLabelPath = "plant_labels.txt"
    private val mSamplePath = "soybean.JPG"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        mClassifier = Classifier(assets, mModelPath, mLabelPath, mInputSize)

        resources.assets.open(mSamplePath).use {
            mBitmap = BitmapFactory.decodeStream(it)
            mBitmap = Bitmap.createScaledBitmap(mBitmap, mInputSize, mInputSize, true)
            mPhotoImageView.setImageBitmap(mBitmap)
        }

        mCameraButton.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(callCameraIntent, mCameraRequestCode)
        }

        mGalleryButton.setOnClickListener {
            val callGalleryIntent = Intent(Intent.ACTION_PICK)
            callGalleryIntent.type = "image/*"
            startActivityForResult(callGalleryIntent, mGalleryRequestCode)
        }
        mDetectButton.setOnClickListener {
                val results = mClassifier.recognizeImage(mBitmap).firstOrNull()
                val default: String = results?.title+"\n Confidence:"+results?.confidence
                val title = results?.title

                if (title == "apple apple scab") {
                    val display = default + "\n Remedy: Rake under trees and destroy infected leaves to reduce the number of fungal spores available to start the disease cycle over again next spring."
                    mResultTextView.text= display
                }
                else if (title == "apple black rot"){
                    val display = default + "\n Remedy: Destroy all infected tissue immediately and keep a watchful eye out for new signs of infection."
                    mResultTextView.text= display
                }
                else if (title == "apple cedar apple rust"){
                    val display = default + "\n Remedy: Remove galls from infected junipers. In some cases, juniper plants should be removed entirely. "
                    mResultTextView.text= display
                }
                else if (title == "apple healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "blueberry healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "cherry including sour powdery mildew"){
                    val display = default + "\n Remedy: Use pesticides with care. Apply them only to plants, animals, or sites listed on the labels."
                    mResultTextView.text= display
                }
                else if (title == "cherry including sour healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "corn maize cercospora leaf spot gray leaf spot"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "corn maize common rust"){
                    val display = default + "\n Remedy: Monitor disease development, crop growth stage and weather forecast"
                    mResultTextView.text= display
                }
                else if (title == "corn maize northern leaf blight"){
                    val display = default + "\n Remedy: Crop rotation to reduce previous corn residues and disease inoculum"
                    mResultTextView.text= display
                }
                else if (title == "corn maize healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "grape black rot"){
                    val display = default + "\n Remedy: Cultural control consists of the management of the fields and sanitation methods to optimally grow grape crops."
                    mResultTextView.text= display
                }
                else if (title == "grape esca black measles"){
                    val display = default + "\n Remedy: Presently, there are no effective management strategies for measles."
                    mResultTextView.text= display
                }
                else if (title == "grape leaf blight isariopsis leaf spot"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "grape healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "orange haunglongbing citrus greening"){
                    val display = default + "\n Remedy: Some cultural practices can be effective in monitoring this disease. Cultural methods include antibacterial management, sanitation, removal of infected plants, frequent scouting, and most importantly, crisis declaration"
                    mResultTextView.text= display
                }
                else if (title == "peach bacterial spot"){
                    val display = default + "\n Remedy: More cultivars are being developed, so check with your local extension office or nursery for new resistant varieties"
                    mResultTextView.text= display
                }
                else if (title == "peach healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "pepper bell bacterial spot"){
                    val display = default + "\n Remedy: Use disease-free seed that has been produced in western states or seed that has been hot water treated. One infested seed in 10,000 may easily result in 100% diseased plants in the field under proper conditions"
                    mResultTextView.text= display
                }
                else if (title == "pepper bell healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "potato early blight"){
                    val display = default + "\n Remedy: Follow a complete and regular foliar fungicide spray program"
                    mResultTextView.text= display
                }
                else if (title == "potato late blight"){
                    val display = default + "\n Remedy: f infected tubers make it into the storage bin, there's a very high risk to the storage life of that bin"
                    mResultTextView.text= display
                }
                else if (title == "potato healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "raspberry healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "soybean healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "squash powdery mildew"){
                    val display = default + "\n Remedy: Along with the perfect storm of weather conditions, we no doubt aided and abetted the disease. As mentioned above, the disease overwinters"
                    mResultTextView.text= display
                }
                else if (title == "strawberry leaf scorch"){
                    val display = default + "\n Remedy: Remove the older and infected leaves from runner plants before setting."
                    mResultTextView.text= display
                }
                else if (title == "strawberry healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }
                else if (title == "tomato bacterial spot"){
                    val display = default + "\n Remedy: In the greenhouse, discard trays adjacent to outbreak location to minimize disease spread."
                    mResultTextView.text= display
                }
                else if (title == "tomato early blight"){
                    val display = default + "\n Remedy: Rotate crops. Practice a 2- or 3-year crop rotation. Avoid planting eggplant or potatoes where tomatoes were last planted."
                    mResultTextView.text= display
                }
                else if (title == "tomato late blight"){
                    val display = default + "\n Remedy: Remove volunteers from the garden prior to planting and space plants far enough apart to allow for plenty of air circulation"
                    mResultTextView.text= display
                }
                else if (title == "tomato leaf mold"){
                    val display = default + "\n Remedy: Depending exclusively upon chemicals isn’t a good management approach. In the case of leaf mold of tomato, using resistant varieties."
                    mResultTextView.text= display
                }
                else if (title == "tomato septoria leaf spot"){
                    val display = default + "\n Remedy: Mulch around the base of the plants. Mulching will reduce splashing soil, which may contain fungal spores associated with debris. Apply mulch after the soil has warmed."
                    mResultTextView.text= display
                }
                else if (title == "tomato spider mites two spotted spider mite"){
                    val display = default + "\n Remedy: bifenazate (Acramite): Group UN, a long residual nerve poison"
                    mResultTextView.text= display
                }
                else if (title == "tomato target spot"){
                    val display = default + "\n Remedy: Do not plant new crops next to older ones that have the disease"
                    mResultTextView.text= display
                }
                else if (title == "tomato tomato yellow leaf curl virus"){
                    val display = default + "\n Remedy: Currently, the most effective treatments used to control the spread of TYLCV are insecticides and resistant crop varieties"
                    mResultTextView.text= display
                }
                else if (title == "tomato tomato mosaic virus"){
                    val display = default + "\n Remedy: Seed catalogs, often with the code ToMV after the variety name if resistant to tomato mosaic virus and TMV if resistant to tobacco mosaic virus. There are only a few varieties that are resistant to both viruses."
                    mResultTextView.text= display
                }
                else if (title == "tomato healthy"){
                    val display = default + "\n Remedy: No Remedy found"
                    mResultTextView.text= display
                }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == mCameraRequestCode){
            //Considérons le cas de la caméra annulée
            if(resultCode == Activity.RESULT_OK && data != null) {
                mBitmap = data.extras!!.get("data") as Bitmap
                mBitmap = scaleImage(mBitmap)
                val toast = Toast.makeText(this, ("Image crop to: w= ${mBitmap.width} h= ${mBitmap.height}"), Toast.LENGTH_LONG)
                toast.setGravity(Gravity.BOTTOM, 0, 20)
                toast.show()
                mPhotoImageView.setImageBitmap(mBitmap)
                mResultTextView.text= "Your photo image set now."
            } else {
                Toast.makeText(this, "Camera cancel..", Toast.LENGTH_LONG).show()
            }
        } else if(requestCode == mGalleryRequestCode) {
            if (data != null) {
                val uri = data.data

                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                println("Success!!!")
                mBitmap = scaleImage(mBitmap)
                mPhotoImageView.setImageBitmap(mBitmap)

            }
        } else {
            Toast.makeText(this, "Unrecognized request code", Toast.LENGTH_LONG).show()

        }
    }


    fun scaleImage(bitmap: Bitmap?): Bitmap {
        val orignalWidth = bitmap!!.width
        val originalHeight = bitmap.height
        val scaleWidth = mInputSize.toFloat() / orignalWidth
        val scaleHeight = mInputSize.toFloat() / originalHeight
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, orignalWidth, originalHeight, matrix, true)
    }

}

