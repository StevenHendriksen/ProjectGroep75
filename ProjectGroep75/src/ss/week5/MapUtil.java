package ss.week5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtil {
	/* @ ensures \result == true if (\forall int i; 0 <= i & i < map.values().size()
	 * ;(\forall int j; 0 <= j & j < map.values().size()
	 * ; map.get(i) != map.get(j + 1);))
	*/
	public static <K, V> boolean isOneOnOne(Map<K, V> map) {
		for (int i = 1; i < map.values().size(); i++) {
			for (int j = i; j < map.values().size(); j++) {
				if (map.get(i) == map.get(j + 1)) {
					return false;
				}
			}
		}
		return true;
	}
	// @ ensures \result == true if (\forall V v : range; map.containsValue(v))
	public static <K, V> boolean isSurjectiveOnRange(Map<K, V> map, Set<V> range) {
		for (V v : range) {
			if (!map.containsValue(v)) {
				return false;
			}

		}
		return true;
	}
	/* @ ensures \forall K key : map.keySet(); result.containsKey(value)? result.put(value, new HashSet<K>());
	 * @ ensures \result == result;
	 */
	public static <K, V> Map<V, Set<K>> inverse(Map<K, V> map) {
		Map<V, Set<K>> result = new HashMap<V, Set<K>>();
		for (K key : map.keySet()) {
			V value = map.get(key);
			if (!result.containsKey(value)) {
				result.put(value, new HashSet<K>());
			}
			result.get(value).add(key);
		}
		return result;
	}
	/* @ ensures isOneonOne(map)? (\forall K key : map.keySet(); result.containsKey(value))
	 * @ ensures \result == result;
	 */
	public static <K, V> Map<V, K> inverseBijection(Map<K, V> map) {
		if (isOneOnOne(map) == true) {
			Map<V, K> result = new HashMap<V, K>();
			for (K key : map.keySet()) {
				V value = map.get(key);
				if (!result.containsKey(value)) {
					result.put(value, key);
				}
			}
			return result;
		}
		return null;
	}
	/* @ ensures \result == false if (\forall int i; 0 <= i & i < f.size() + 1
	 * ; !g.containsKey(f.get(i)))
	 */
	public static <K, V, W> boolean compatible(Map<K, V> f, Map<V, W> g) {
		for (int i = 1; i < f.size() + 1; i++) {
			if (!g.containsKey(f.get(i))) {
				return false;
			}
		}

		return true;
	}
	/* @ ensures compatible? (\forall K key : f.keySet(); map.put(key, g.get(f.get(key)))
	 * @ ensures \result == map;
	 */
	public static <K, V, W> Map<K, W> compose(Map<K, V> f, Map<V, W> g) {
		Map<K, W> map = new HashMap<K, W>();
		if (compatible(f, g)) {
			for (K key : f.keySet()) {
				map.put(key, g.get(f.get(key)));
			}
		}
		return map;
	}
}