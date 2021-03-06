#- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
#- Limelight and all included source files are distributed under terms of the GNU LGPL.

on_key_pressed do |e|
  print("#{e.key_code} pressed")
end

on_char_typed do |e|
  print("#{e.char} typed")
end

on_key_released do |e|
  print("#{e.key_code} released")
end

on_focus_gained do
  print("gained focused")
end

on_focus_lost do
  print("lost focus")
end

on_button_pushed do
  print("pressed")
end

backstage_reader :radio_button

def print(value)
  log = scene.find("radio_button_log")
  log.text += "#{id}:#{value}\n"

  results = scene.find("radio_button_results")
  radio1 = scene.find("radio_1")
  radio2 = scene.find("radio_2")
  radio3 = scene.find("radio_3")
  results.text = "One: #{radio1.radio_button.selected?}\nTwo: #{radio2.radio_button.selected?}\nThree: #{radio3.radio_button.selected?}"
end
