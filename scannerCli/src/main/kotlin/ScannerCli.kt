package org.ossreviewtoolkit.scannercli

import org.ossreviewtoolkit.scanner.Scanner

/**
 * The entry point for the application with [args] being the list of arguments.
 */
//fun main(args: Array<String>) {
//    println("test $args")
//}

class ScannerCli {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello from ScannerCLI. List of all scanners: ${Scanner.ALL}")
        }
    }

}
