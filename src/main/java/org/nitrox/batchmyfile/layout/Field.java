/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.layout;

/**
 *
 * @author 03883182443
 */
public interface Field {
    
    public int size();
    
    public String getName();
    
    public String getAtributeName();
    
    public FieldType getType();
    
    public void getPartFile();
    
    public boolean isObligatory();
    
    public Layout getLayout();
}
