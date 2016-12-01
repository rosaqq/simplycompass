package net.secknv.scomp.util;

import net.minecraftforge.fml.common.FMLLog;
import net.secknv.scomp.reference.Reference;
import org.apache.logging.log4j.Level;

/*
 * MIT License
 *
 * Copyright (c) 2016 secknv
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

public class LogHelper {

    public static void log(Level logLevel, Object obj) {

        FMLLog.log(Reference.MOD_NAME, logLevel, String.valueOf(obj));
    }

    public static void off (Object obj) {log(Level.OFF, obj);}
    public static void fatal (Object obj) {log(Level.FATAL, obj);}
    public static void error (Object obj) {log(Level.ERROR, obj);}
    public static void warn (Object obj) {log(Level.WARN, obj);}
    public static void info (Object obj) {log(Level.INFO, obj);}
    public static void debug (Object obj) {log(Level.DEBUG, obj);}
    public static void trace (Object obj) {log(Level.TRACE, obj);}
    public static void all (Object obj) {log(Level.ALL, obj);}

}
