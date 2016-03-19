package org.bimserver.ifcvalidator.checks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ModelCheckerRegistry {
	private final Map<String, Map<String, ModelCheck>> modelChecks = new LinkedHashMap<>();
	
	private void addCheck(ModelCheck modelCheck) {
		Map<String, ModelCheck> map = modelChecks.get(modelCheck.getGroupIdentifier());
		if (map == null) {
			map = new LinkedHashMap<String, ModelCheck>();
			modelChecks.put(modelCheck.getGroupIdentifier(), map);
		}
		map.put(modelCheck.getIdentifier(), modelCheck);
	}
	
	public ModelCheckerRegistry() {
		addCheck(new OnlyOneIfcProject());

		addCheck(new CheckUnits());
		addCheck(new HasTrueNorthSet());

		addCheck(new OnlyOneIfcSite());
		addCheck(new IfcSiteElevation());
		addCheck(new IfcSiteKadastaleAanduiding());
		addCheck(new IfcSiteLatitude());
		addCheck(new IfcSiteLongitude());

		addCheck(new AtLeastOneBuilding());

		addCheck(new AllObjectsInBuildingStorey());
		
		addCheck(new BuildingStoreyNamesAndZOrder());
	}
	
	public Set<String> getGroupIdentifiers() {
		return modelChecks.keySet();
	}

	public ModelCheck getModelCheck(String groupIdentifer, String identifier) {
		return modelChecks.get(groupIdentifer).get(identifier);
	}

	public Set<String> getIdentifiers(String groupIdentifier) {
		return modelChecks.get(groupIdentifier).keySet();
	}
}