#- Copyright 2008 8th Light, Inc.
#- Limelight and all included source files are distributed under terms of the GNU LGPL.

__ :name => "sandbox"
__install "header.rb"
arena do
  surface
  floater :x => "100", :y => "100", :text => "Thing 1"
  floater :x => "600", :y => "600", :text => "Thing 2", :background_color => "#e59846bb", :border_color => "#b75f01"
end