package io.sy.ffmpegxutil;

/**
 * @Author: shiyi
 * @Date: 2020/8/27 12:08 AM
 */
public class RxFFmpegUtil {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avdevice");
        System.loadLibrary("avfilter");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("postproc");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");
    }

    private static OnCmdExecListener sOnCmdExecListener;
    private static long sDuration;

    private static native int exec(int cmdLength , String[] cmd);
    private static native void exit();

    public int synchronizedExeCmd(String[] cmd,long duration, OnCmdExecListener listener) {
        sOnCmdExecListener = listener;
        sDuration = duration;
        int exec = exec(cmd.length, cmd);
        return exec;
    }

    /**
     * FFmpeg执行结束回调，由C代码中调用
     */
    public static void onExecutedCmd(int ret) {
        if (sOnCmdExecListener != null) {
            if (ret == 0) {
                sOnCmdExecListener.onProgress(sDuration);
                sOnCmdExecListener.onSuccess();
            } else {
                sOnCmdExecListener.onFailure();
            }
        }
    }

    /**
     * FFmpeg执行进度回调，由C代码调用
     */
    public static void onFFmpegProgress(float progress) {
        if (sOnCmdExecListener != null) {
            if (sDuration != 0) {
//                sOnCmdExecListener.onProgress(progress / (sDuration / 1000) * 0.95f);
                sOnCmdExecListener.onProgress(progress);
            }
        }
    }

    public interface OnCmdExecListener {
        void onSuccess();

        void onFailure();

        void onProgress(float progress);
    }
}
