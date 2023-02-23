package hyunsoo.`24week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [트리와 쿼리](https://www.acmicpc.net/problem/15681)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 * - 전체 NodeData 세팅 후 SubTree 세팅을 따로 해줬더니 시간초과 발생
 * - 인접 행렬은 메모리 초과
 */
class `전현수_트리와_쿼리` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))

    // 부모로 연결이 된 노드의 번호를 저장
    private val connectedParentList = mutableListOf<Int>()

    // 입력으로 주어지는 기본 트리의 정보를 인접리스트로 저장
    private lateinit var myTree: Array<MutableList<Int>>

    // 서브 트리의 노드의 수가 저장 됨.
    private lateinit var subTreeData: IntArray

    fun solution() {

        val (vertexCnt, rootNum, queryCnt) = br.readLine().split(" ").map { it.toInt() }

        myTree = Array(vertexCnt + 1) {
            mutableListOf()
        }

        subTreeData = IntArray(vertexCnt + 1) { 1 }

        repeat(vertexCnt - 1) {
            val (startVertex, endVertex) = br.readLine().split(" ").map { it.toInt() }
            myTree[startVertex].add(endVertex)
            myTree[endVertex].add(startVertex)
        }

        setSubtreeCnt(rootNum)

        repeat(queryCnt) {
            val query = br.readLine().toInt()
            bw.write("${subTreeData[query]}\n")
        }

        bw.flush()
        bw.close()
    }

    private fun setSubtreeCnt(targetNode: Int) {

        connectedParentList.add(targetNode)

        myTree[targetNode].forEach { childNode ->

            myTree[childNode].remove(targetNode)

            // 자식으로 재귀
            setSubtreeCnt(childNode)
            // 자식의 서브 트리 정점 수를 더해줌.
            subTreeData[targetNode] += subTreeData[childNode]
        }
    }

}

fun main() {
    전현수_트리와_쿼리().solution()
}