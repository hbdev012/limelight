#- Copyright 2008 8th Light, Inc.
#- Limelight and all included source files are distributed under terms of the GNU LGPL.

module Sample
  def mouse_clicked(e)
    puts "mouse clicked"
    selection = scene.find('the_selection')
    selection.style.background_color = self.style.background_color
    selection.update
  end
end