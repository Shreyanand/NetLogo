// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.gui

import org.nlogo.api.{ ReporterRunnable}
import org.nlogo.core.Syntax
import org.nlogo.awt.UserCancelException
import org.nlogo.nvm.{ Context, Reporter }
import org.nlogo.nvm.RuntimePrimitiveException
import org.nlogo.window.GUIWorkspace
import org.nlogo.workspace.AbstractWorkspace.isApplet
import org.nlogo.swing.FileDialog

class _userfile extends Reporter {



  override def report(context: Context) = {
    if (isApplet)
      throw new RuntimePrimitiveException(
        context, this, "You cannot choose a file from an applet.")
    var result: AnyRef = null
    workspace match {
      case gw: GUIWorkspace =>
        gw.updateUI()
        result = gw.waitForResult(
          new ReporterRunnable[AnyRef] {
            override def run() =
              try {
                gw.view.mouseDown(false)
                FileDialog.setDirectory(workspace.fileManager.prefix)
                FileDialog.show(gw.getFrame, "Choose File", java.awt.FileDialog.LOAD)
              }
              catch {
                case _: UserCancelException =>
                  java.lang.Boolean.FALSE
              }})
      case _ =>
        throw new RuntimePrimitiveException(
          context, this, "You can't get user input headless.")
    }
    result match {
      case null =>
        throw new org.nlogo.nvm.HaltException(false)
      case b: java.lang.Boolean =>
        b
      case s: String =>
        if(!new java.io.File(s).exists)
          throw new RuntimePrimitiveException(
            context, this, "This file doesn't exist")
        result
    }
  }

}
