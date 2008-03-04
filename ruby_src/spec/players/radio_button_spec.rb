require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")
require 'limelight/page'
require 'limelight/prop'
require 'limelight/players/radio_button'

describe Limelight::Players::RadioButton do

  before(:each) do
    @page = Limelight::Page.new(:illuminator => make_mock("caster", :fill_cast => nil))
    @prop = Limelight::Prop.new
    @page << @prop
    @prop.include_player(Limelight::Players::RadioButton)
  end
  
  it "should get rid of the all painters and add a RadioButtonPainter" do
    @prop.panel.painters.size.should == 1
    @prop.panel.painters.last.class.should == Java::limelight.ui.painting.RadioButtonPainter
  end
  
  it "should clear event listeners on the panel" do
    @prop.panel.mouse_listeners.length.should == 0
    @prop.panel.key_listeners.length.should == 0
  end
  
  it "should have a RadioButton" do
    @prop.panel.components[0].class.should == javax.swing.JRadioButton
  end
  
  it "should handled checked state" do
    @prop.checked?.should == false
    @prop.checked.should == false
    @prop.selected?.should == false
    @prop.selected.should == false
    
    @prop.selected = true
    
    @prop.checked?.should == true
    @prop.checked.should == true
    @prop.selected?.should == true
    @prop.selected.should == true
    @prop.panel.components[0].is_selected.should == true
  end
  
  it "should belong to a button group" do
    @prop.group = "group 1"
    
    prop2 = Limelight::Prop.new
    @page << prop2
    prop2.add_controller(Limelight::Players::RadioButton)
    prop2.group = "group 1"
    
    prop3 = Limelight::Prop.new
    @page << prop3
    prop3.add_controller(Limelight::Players::RadioButton)
    prop3.group = "group 2"
    
    group1 = @page.button_groups["group 1"]
    group1.elements.should include(@prop.radio_button)
    group1.elements.should include(prop2.radio_button)
    group1.elements.should_not include(prop3.radio_button)
    
    group2 = @page.button_groups["group 2"]
    group2.elements.should_not include(@prop.radio_button)
    group2.elements.should_not include(prop2.radio_button)
    group2.elements.should include(prop3.radio_button)
    
    @prop.button_group.should == group1
    prop2.button_group.should == group1
    prop3.button_group.should == group2
  end

end