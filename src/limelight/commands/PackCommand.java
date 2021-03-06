//- Copyright © 2008-2011 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the MIT License.

package limelight.commands;

import limelight.Context;
import limelight.io.Packer;

import java.util.Map;

public class PackCommand extends Command
{
  private static Arguments arguments;
  private Packer packer;

  public static Arguments arguments()
  {
    if(arguments == null)
    {
      arguments = new Arguments();
      arguments.addParameter("production", "Path to production folder to be packed");
      arguments.addValueOption("n", "name", "llp-name", "Name of the generated llp file.  Defaults to the production folder name.");
    }
    return arguments;
  }

  @Override
  public void doExecute(Map<String, String> args)
  {
    String productionDir = args.get("production");
    String llpName = args.get("name");
    if(llpName != null)
      getPacker().pack(productionDir, llpName);
    else
      getPacker().pack(productionDir, Context.fs().baseName(productionDir));
  }

  private Packer getPacker()
  {
    if(packer == null)
      packer = new Packer();
    return packer;
  }

  public void setPacker(Packer packer)
  {
    this.packer = packer;
  }

  @Override
  public String description()
  {
    return "Pack a limelight production into a .llp file.";
  }

  @Override
  public String name()
  {
    return "pack";
  }

  @Override
  public Arguments getArguments()
  {
    return arguments();
  }
}
