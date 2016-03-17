package org.B2Rolling;

import java.util.ArrayList;

import org.cocos2d.nodes.CCNode;

public class TemplateDefMgr extends CCNode {
	
	public ArrayList<TemplateDefInfo> m_TmpDefsInfoArray;
	
	private TemplateDefMgr() {
		m_TmpDefsInfoArray = new ArrayList<TemplateDefInfo>();
		makeTmpInfo();
	}
	
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		m_TmpDefsInfoArray.removeAll(m_TmpDefsInfoArray);
		super.onExit();
	}

	private void makeTmpInfo() {
	    setTmpDefInfo(GameConfig.TemplateType.street_anchor15, 15, 15, "properties-street-pivot.png", GameConfig.PhysicalType.STEEL_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.sewer_anchor15, 15, 15, "properties-sewer-pivot.png", GameConfig.PhysicalType.STEEL_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.prison_anchor15, 15, 15, "properties-prison-pivot.png", GameConfig.PhysicalType.STEEL_TYPE); 
	    setTmpDefInfo(GameConfig.TemplateType.factory_anchor15, 15, 15, "properties-factory-pivot.png", GameConfig.PhysicalType.STEEL_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.ball15, 15, 15, "properties-ball30.png", GameConfig.PhysicalType.STEELBALL_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.box26, 26, 26, "properties-barrier-box26.png", GameConfig.PhysicalType.WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.box30, 30, 30, "properties-barrier-box30.png", GameConfig.PhysicalType.WOOD_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.wood80, 80, 10, "properties-bar-wood80.png", GameConfig.PhysicalType.WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.wood100, 100, 10, "properties-bar-wood100.png", GameConfig.PhysicalType.WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.wood120, 120, 10, "properties-bar-wood120.png", GameConfig.PhysicalType.WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.wood150, 150, 10, "properties-bar-wood150.png", GameConfig.PhysicalType.WOOD_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.staticwood80, 80, 10, "properties-bar-staticwood80.png", GameConfig.PhysicalType.STATIC_WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.staticwood100, 100, 10, "properties-bar-staticwood100.png", GameConfig.PhysicalType.STATIC_WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.staticwood120, 120, 10, "properties-bar-staticwood120.png", GameConfig.PhysicalType.STATIC_WOOD_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.staticwood150, 150, 10, "properties-bar-staticwood150.png", GameConfig.PhysicalType.STATIC_WOOD_TYPE);    
	    
	    setTmpDefInfo(GameConfig.TemplateType.brick20, 20, 15, "properties-street-terrace-brick20.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick40, 40, 15, "properties-street-terrace-brick40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick60, 60, 15, "properties-street-terrace-brick60.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick80, 80, 15, "properties-street-terrace-brick80.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick100, 100, 15, "properties-street-terrace-brick100.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick120, 120, 15, "properties-street-terrace-brick120.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick150, 150, 15, "properties-street-terrace-brick150.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick200, 200, 15, "properties-street-terrace-brick200.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.brick400, 400, 15, "properties-street-terrace-brick400.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stage2_20, 20, 15, "properties-sewer-terrace-stone20.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_40, 40, 15, "properties-sewer-terrace-stone40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_60, 60, 15, "properties-sewer-terrace-stone60.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_80, 80, 15, "properties-sewer-terrace-stone80.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_100, 100, 15, "properties-sewer-terrace-stone100.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_120, 120, 15, "properties-sewer-terrace-stone120.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_150, 150, 15, "properties-sewer-terrace-stone150.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_200, 200, 15, "properties-sewer-terrace-stone200.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage2_400, 400, 15, "properties-sewer-terrace-stone400.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stage3_20, 20, 15, "properties-prison-terrace-cement20.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_40, 40, 15, "properties-prison-terrace-cement40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_60, 60, 15, "properties-prison-terrace-cement60.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_80, 80, 15, "properties-prison-terrace-cement80.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_100, 100, 15, "properties-prison-terrace-cement100.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_120, 120, 15, "properties-prison-terrace-cement120.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_150, 150, 15, "properties-prison-terrace-cement150.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_200, 200, 15, "properties-prison-terrace-cement200.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_400, 400, 15, "properties-prison-terrace-cement400.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stage4_20, 20, 15, "properties-factory-terrace-iron20.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_40, 40, 15, "properties-factory-terrace-iron40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_60, 60, 15, "properties-factory-terrace-iron60.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_80, 80, 15, "properties-factory-terrace-iron80.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_100, 100, 15, "properties-factory-terrace-iron100.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_120, 120, 15, "properties-factory-terrace-iron120.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_150, 150, 15, "properties-factory-terrace-iron150.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_200, 200, 15, "properties-factory-terrace-iron200.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_400, 400, 15, "properties-factory-terrace-iron400.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stone40, 40, 15, "properties-bar-stone-40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone60, 60, 15, "properties-bar-stone-60.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone80, 80, 15, "properties-bar-stone-80.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone100, 100, 15, "properties-bar-stone-100.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone120, 120, 15, "properties-bar-stone-120.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone150, 150, 15, "properties-bar-stone-150.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone200, 200, 15, "properties-bar-stone-200.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stone400, 400, 15, "properties-bar-stone-400.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.cement40, 40, 15, "properties-bar-cement-1.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement60, 60, 15, "properties-bar-cement-2.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement80, 80, 15, "properties-bar-cement-3.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement100, 100, 15, "properties-bar-cement-4.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement120, 120, 15, "properties-bar-cement-5.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement150, 150, 15, "properties-bar-cement-6.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.cement200, 200, 15, "properties-bar-cement-7.png", GameConfig.PhysicalType.CEMENT_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stage3_triangle40, 40, 40, "properties-stage3-triangle40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage3_triangle30, 30, 30, "properties-stage3-triangle30.png", GameConfig.PhysicalType.STONE_TYPE);
	    
	    setTmpDefInfo(GameConfig.TemplateType.stage4_triangle40, 40, 40, "properties-stage4-triangle40.png", GameConfig.PhysicalType.STONE_TYPE);
	    setTmpDefInfo(GameConfig.TemplateType.stage4_triangle30, 30, 30, "properties-stage4-triangle30.png", GameConfig.PhysicalType.STONE_TYPE);
	}

	private void setTmpDefInfo(int ntempType, int width, int height, String str, int physicsType)
	{
	    TemplateDefInfo info = new TemplateDefInfo();
	    
	    info.setTmpDefInfo(ntempType, width, height, str, physicsType);
	    m_TmpDefsInfoArray.add(info);
	}
	
	public static void createTmpDefMgr() {
		G.g_tmplateDefMgr = new TemplateDefMgr();
	}
}
