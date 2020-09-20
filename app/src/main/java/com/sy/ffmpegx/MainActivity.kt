package com.sy.ffmpegx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.sy.ffmpegxutil.RxFFmpegUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val path = "/sdcard/syytest.mp4"
    private val pathMkv = "/sdcard/syytest.mkv"
    private val pathFlv = "/sdcard/syytestflv.flv"
    private val pathOthermp = "/sdcard/syytestother.mp4"
    private val path_output = "/sdcard/output.gif"
    //    private val path_output1 = "/sdcard/outputvideo.mp4"
    private val path_output1 = "/sdcard/testsy.mp4"
    private val path_output1_avi = "/sdcard/outputavi.avi"
    private val path_output_filter = "/sdcard/outputvideofilter.mp4"
    private val path_output_path = "/sdcard/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text11.setOnClickListener {
//            RxFFmpegUtil.exit11()
        }

        //        val cmd = "ffmpeg -i $path"
//        val cmd = "ffmpeg -i $path -ss 00:00:00 -t 10 -y $path_output" //提取视频前10秒转gif
//        val cmd = "ffmpeg -i $path_output -y $path_output1"  //gif转视频
//        val cmd ="ffmpeg -i $path -vf fps=2 /sdcard/o1ut%03d.png"  //提起视频中的图片
//        val cmd="ffmpeg -i $pathMkv -y $pathOthermp" //格式转换
//        val cmd="ffmpeg -i $pathMkv -vcodec copy -an /sdcard/syytestoutmk.mp4" //格式转换
//        val cmd="ffmpeg -i $path -vn -a:c copy /sdcard/out.mp3" //提取视频中的音频  不可用
        val cmd="ffmpeg -i $path -acodec copy -vn /sdcard/out11.aac" //提取mkv视频中的音频
//        val cmd="ffmpeg -i $path -strict -2 -vf crop=1210:488:100:100 $path_output_filter" //裁剪
//        val cmd="ffmpeg -i $path -strict -2 -vf scale=640:-1 $path_output_filter"  //缩放

//        val cmd ="ffmpeg -i /sdcard/out003.png -vf smartblur=5:0.8:0 /sdcard/blurred_halftone.png"
        //没有添加freetype字库，所以无法使用drawtext
//        val cmd="ffmpeg -y -i $path_output1 -vf \"drawtext=fontfile=CourierNew.ttf:text='helloworld':x=100:y=50:fontsize=24\" $path_output_filter"

        val split = cmd.split(" ".toRegex()).toTypedArray()
        RxFFmpegUtil().synchronizedExeCmd(split,100, object :
            RxFFmpegUtil.OnCmdExecListener{
            override fun onSuccess() {
                Log.e("MainActivity","onSuccess()")
            }

            override fun onFailure() {
                Log.e("MainActivity","onFailure")
            }

            override fun onProgress(progress: Float) {
                Log.e("MainActivity","progress == $progress")
            }
        })

    }
}