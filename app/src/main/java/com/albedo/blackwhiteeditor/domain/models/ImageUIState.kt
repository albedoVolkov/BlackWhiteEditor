package com.albedo.blackwhiteeditor.domain.models


import com.google.gson.Gson


/**
 * Creates resized images without exploding memory. Uses the method described in android
 * documentation concerning bitmap allocation, which is to subsample the image to a smaller size,
 * close to some expected size. This is required because the android standard library is unable to
 * create a reduced size image from an image file using memory comparable to the final size (and
 * loading a full sized multi-megapixel picture for processing may exceed application memory budget).
 *
 * implementation by user @hdante
 * http://stackoverflow.com/users/1797000/hdante
 */
class ImageUIState { // <- var uri: Uri, var resolver: ContentResolver
//    var path: String? = null
//    var orientation: Matrix? = null
//    var storedHeight = 0
//    var storedWidth = 0
//
//    @get:Throws(IOException::class)
//    private val information: Boolean
//        private get() {
//            if (informationFromMediaDatabase) return true
//            return if (informationFromFileSystem) true else false
//        }
//    private val informationFromMediaDatabase: Boolean
//        /* Support for gallery apps and remote ("picasa") images */private get() {
//            val fields =
//                arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION)
//            val cursor = resolver.query(uri, fields, null, null, null) ?: return false
//            cursor.moveToFirst()
//            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
//            val orientation =
//                cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION))
//            this.orientation = Matrix()
//            this.orientation!!.setRotate(orientation.toFloat())
//            cursor.close()
//            return true
//        }
//
//    @get:Throws(IOException::class)
//    private val informationFromFileSystem: Boolean
//        /* Support for file managers and dropbox */ private get() {
//            path = uri.path
//            if (path == null) return false
//            val exif = ExifInterface(path!!)
//            val orientation = exif.getAttributeInt(
//                ExifInterface.TAG_ORIENTATION,
//                ExifInterface.ORIENTATION_NORMAL
//            )
//            this.orientation = Matrix()
//            when (orientation) {
//                ExifInterface.ORIENTATION_NORMAL -> {}
//                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> this.orientation!!.setScale(-1f, 1f)
//                ExifInterface.ORIENTATION_ROTATE_180 -> this.orientation!!.setRotate(180f)
//                ExifInterface.ORIENTATION_FLIP_VERTICAL -> this.orientation!!.setScale(1f, -1f)
//                ExifInterface.ORIENTATION_TRANSPOSE -> {
//                    this.orientation!!.setRotate(90f)
//                    this.orientation!!.postScale(-1f, 1f)
//                }
//
//                ExifInterface.ORIENTATION_ROTATE_90 -> this.orientation!!.setRotate(90f)
//                ExifInterface.ORIENTATION_TRANSVERSE -> {
//                    this.orientation!!.setRotate(-90f)
//                    this.orientation!!.postScale(-1f, 1f)
//                }
//
//                ExifInterface.ORIENTATION_ROTATE_270 -> this.orientation!!.setRotate(-90f)
//            }
//            return true
//        }
//
//    @get:Throws(IOException::class)
//    private val storedDimensions: Boolean
//        private get() {
//            val input = resolver.openInputStream(uri)
//            val options = BitmapFactory.Options()
//            options.inJustDecodeBounds = true
//            BitmapFactory.decodeStream(resolver.openInputStream(uri), null, options)
//
//            /* The input stream could be reset instead of closed and reopened if it were possible
//               to reliably wrap the input stream on a buffered stream, but it's not possible because
//               decodeStream() places an upper read limit of 1024 bytes for a reset to be made (it calls
//               mark(1024) on the stream). */input!!.close()
//            if (options.outHeight <= 0 || options.outWidth <= 0) return false
//            storedHeight = options.outHeight
//            storedWidth = options.outWidth
//            return true
//        }
//
//    @get:Throws(IOException::class)
//    val bitmap: Bitmap?
//        get() {
//            if (!information) throw FileNotFoundException()
//            if (!storedDimensions) throw InvalidObjectException(null)
//            val rect = RectF(0f, 0f, storedWidth.toFloat(), storedHeight.toFloat())
//            orientation!!.mapRect(rect)
//            var width = rect.width().toInt()
//            var height = rect.height().toInt()
//            var subSample = 1
//            while (width > MAX_WIDTH || height > MAX_HEIGHT) {
//                width /= 2
//                height /= 2
//                subSample *= 2
//            }
//            if (width == 0 || height == 0) throw InvalidObjectException(null)
//            val options = BitmapFactory.Options()
//            options.inSampleSize = subSample
//            val subSampled = BitmapFactory.decodeStream(
//                resolver.openInputStream(
//                    uri
//                ), null, options
//            )
//            val picture: Bitmap?
//            if (!orientation!!.isIdentity) {
//                picture = Bitmap.createBitmap(
//                    subSampled!!, 0, 0, options.outWidth, options.outHeight,
//                    orientation, false
//                )
//                subSampled.recycle()
//            } else picture = subSampled
//            return picture
//        }
//
//    companion object {
//        var MAX_WIDTH = 600
//        var MAX_HEIGHT = 800
//    }

    override fun toString(): String {
        return Gson().toJson(this,ImageUIState::class.java)
    }
}

    fun fromStringToImageUIStateItem(string: String): ImageUIState? {
        return try {
            Gson().fromJson(string, ImageUIState::class.java)
        }catch(e : Exception){
            null
        }
    }