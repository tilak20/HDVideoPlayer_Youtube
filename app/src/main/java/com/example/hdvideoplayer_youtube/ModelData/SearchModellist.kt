package com.downloadersocial.fastvideodownloader.Model.SearchModel

import com.google.gson.annotations.SerializedName

data class SearchModellist(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("contents")
	val contents: Contents? = null,

	@field:SerializedName("responseContext")
	val responseContext: ResponseContext? = null,

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("topbar")
	val topbar: Topbar? = null,

	@field:SerializedName("estimatedResults")
	val estimatedResults: String? = null,

	@field:SerializedName("refinements")
	val refinements: List<String?>? = null
)

data class VoiceSearchButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ReelPlayerOverlayRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("reelPlayerNavigationModel")
	val reelPlayerNavigationModel: String? = null
)

data class NavigationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null,

	@field:SerializedName("reelWatchEndpoint")
	val reelWatchEndpoint: ReelWatchEndpoint? = null,

	@field:SerializedName("userFeedbackEndpoint")
	val userFeedbackEndpoint: UserFeedbackEndpoint? = null,

	@field:SerializedName("continuationCommand")
	val continuationCommand: ContinuationCommand? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("signInEndpoint")
	val signInEndpoint: SignInEndpoint? = null
)

data class ToggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ChannelThumbnailWithLinkRenderer(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class Style(

	@field:SerializedName("styleType")
	val styleType: String? = null
)

data class OriginalQueryEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class PlayerExtraUrlParamsItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class SearchSubMenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null
)

data class ThumbnailOverlayNowPlayingRenderer(

	@field:SerializedName("text")
	val text: Text? = null
)

data class Endpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null
)

data class ChannelThumbnailSupportedRenderers(

	@field:SerializedName("channelThumbnailWithLinkRenderer")
	val channelThumbnailWithLinkRenderer: ChannelThumbnailWithLinkRenderer? = null
)

data class PrimaryContents(

	@field:SerializedName("sectionListRenderer")
	val sectionListRenderer: SectionListRenderer? = null
)

data class LongBylineText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class TopbarMenuButtonRenderer(

	@field:SerializedName("menuRequest")
	val menuRequest: MenuRequest? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("style")
	val style: String? = null
)

data class OnCreateListCommand(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("createPlaylistServiceEndpoint")
	val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class UntoggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class Command(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("showReloadUiCommand")
	val showReloadUiCommand: ShowReloadUiCommand? = null
)

data class MicrophoneOffPromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SampledThumbnailColor(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("green")
	val green: Int? = null,

	@field:SerializedName("blue")
	val blue: Int? = null
)

data class NextButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class Icon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("shareEntityServiceEndpoint")
	val shareEntityServiceEndpoint: ShareEntityServiceEndpoint? = null
)

data class ContentsItem(

	@field:SerializedName("continuationItemRenderer")
	val continuationItemRenderer: ContinuationItemRenderer? = null,

	@field:SerializedName("itemSectionRenderer")
	val itemSectionRenderer: ItemSectionRenderer? = null,

	@field:SerializedName("videoRenderer")
	val videoRenderer: VideoRenderer? = null,

	@field:SerializedName("radioRenderer")
	val radioRenderer: RadioRenderer? = null,

	@field:SerializedName("shelfRenderer")
	val shelfRenderer: ShelfRenderer? = null,

	@field:SerializedName("reelShelfRenderer")
	val reelShelfRenderer: ReelShelfRenderer? = null,

	@field:SerializedName("showingResultsForRenderer")
	val showingResultsForRenderer: ShowingResultsForRenderer? = null
)

data class TooltipText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class CollapsedStateButtonText(

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ResponseContext(

	@field:SerializedName("serviceTrackingParams")
	val serviceTrackingParams: List<ServiceTrackingParamsItem?>? = null,

	@field:SerializedName("webResponseContextExtensionData")
	val webResponseContextExtensionData: WebResponseContextExtensionData? = null,

	@field:SerializedName("visitorData")
	val visitorData: String? = null,

	@field:SerializedName("mainAppWebResponseContext")
	val mainAppWebResponseContext: MainAppWebResponseContext? = null
)

data class ThumbnailOverlaysItem(

	@field:SerializedName("thumbnailOverlayLoadingPreviewRenderer")
	val thumbnailOverlayLoadingPreviewRenderer: ThumbnailOverlayLoadingPreviewRenderer? = null,

	@field:SerializedName("thumbnailOverlayNowPlayingRenderer")
	val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null,

	@field:SerializedName("thumbnailOverlayToggleButtonRenderer")
	val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,

	@field:SerializedName("thumbnailOverlayTimeStatusRenderer")
	val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null,

	@field:SerializedName("thumbnailOverlayHoverTextRenderer")
	val thumbnailOverlayHoverTextRenderer: ThumbnailOverlayHoverTextRenderer? = null,

	@field:SerializedName("thumbnailOverlayBottomPanelRenderer")
	val thumbnailOverlayBottomPanelRenderer: ThumbnailOverlayBottomPanelRenderer? = null
)

data class VideoCountShortText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ContinuationCommand(

	@field:SerializedName("request")
	val request: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("command")
	val command: Command? = null
)

data class UntoggledServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null,

	@field:SerializedName("playlistEditEndpoint")
	val playlistEditEndpoint: PlaylistEditEndpoint? = null
)

data class ShareEntityServiceEndpoint(

	@field:SerializedName("serializedShareEntity")
	val serializedShareEntity: String? = null,

	@field:SerializedName("commands")
	val commands: List<CommandsItem?>? = null
)

data class PublishedTimeText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class ThumbnailOverlayLoadingPreviewRenderer(

	@field:SerializedName("text")
	val text: Text? = null
)

data class ExampleQuery2(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class DesktopTopbarRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("topbarButtons")
	val topbarButtons: List<TopbarButtonsItem?>? = null,

	@field:SerializedName("a11ySkipNavigationButton")
	val a11ySkipNavigationButton: A11ySkipNavigationButton? = null,

	@field:SerializedName("hotkeyDialog")
	val hotkeyDialog: HotkeyDialog? = null,

	@field:SerializedName("forwardButton")
	val forwardButton: ForwardButton? = null,

	@field:SerializedName("backButton")
	val backButton: BackButton? = null,

	@field:SerializedName("voiceSearchButton")
	val voiceSearchButton: VoiceSearchButton? = null,

	@field:SerializedName("logo")
	val logo: Logo? = null,

	@field:SerializedName("searchbox")
	val searchbox: Searchbox? = null
)

data class RunsItem(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("italics")
	val italics: Boolean? = null,

	@field:SerializedName("bold")
	val bold: Boolean? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class MenuNavigationItemRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class SignInEndpoint(

	@field:SerializedName("idamTag")
	val idamTag: String? = null
)

data class FusionSearchboxRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clearButton")
	val clearButton: ClearButton? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("placeholderText")
	val placeholderText: PlaceholderText? = null,

	@field:SerializedName("config")
	val config: Config? = null
)

data class A11ySkipNavigationButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ShowingResultsFor(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SearchHeaderRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("chipBar")
	val chipBar: ChipBar? = null,

	@field:SerializedName("searchFilterButton")
	val searchFilterButton: SearchFilterButton? = null
)

data class PlaceholderHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class CreatePlaylistServiceEndpoint(

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("videoIds")
	val videoIds: List<String?>? = null
)

data class ToggledServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("playlistEditEndpoint")
	val playlistEditEndpoint: PlaylistEditEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class ReelItemRenderer(

	@field:SerializedName("inlinePlaybackEndpoint")
	val inlinePlaybackEndpoint: InlinePlaybackEndpoint? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("viewCountText")
	val viewCountText: ViewCountText? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null,

	@field:SerializedName("videoType")
	val videoType: String? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("menu")
	val menu: Menu? = null,

	@field:SerializedName("headline")
	val headline: Headline? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class ServiceTrackingParamsItem(

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("params")
	val params: List<ParamsItem?>? = null
)

data class ChipCloudChipRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("isSelected")
	val isSelected: Boolean? = null,

	@field:SerializedName("style")
	val style: Style? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class AdditionalDatasItem(

	@field:SerializedName("userFeedbackEndpointProductSpecificValueData")
	val userFeedbackEndpointProductSpecificValueData: UserFeedbackEndpointProductSpecificValueData? = null
)

data class VideoRenderer(

	@field:SerializedName("lengthText")
	val lengthText: LengthText? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("detailedMetadataSnippets")
	val detailedMetadataSnippets: List<DetailedMetadataSnippetsItem?>? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("shortBylineText")
	val shortBylineText: ShortBylineText? = null,

	@field:SerializedName("menu")
	val menu: Menu? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("ownerText")
	val ownerText: OwnerText? = null,

	@field:SerializedName("richThumbnail")
	val richThumbnail: RichThumbnail? = null,

	@field:SerializedName("longBylineText")
	val longBylineText: LongBylineText? = null,

	@field:SerializedName("badges")
	val badges: List<BadgesItem?>? = null,

	@field:SerializedName("searchVideoResultEntityKey")
	val searchVideoResultEntityKey: String? = null,

	@field:SerializedName("inlinePlaybackEndpoint")
	val inlinePlaybackEndpoint: InlinePlaybackEndpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showActionMenu")
	val showActionMenu: Boolean? = null,

	@field:SerializedName("publishedTimeText")
	val publishedTimeText: PublishedTimeText? = null,

	@field:SerializedName("viewCountText")
	val viewCountText: ViewCountText? = null,

	@field:SerializedName("ownerBadges")
	val ownerBadges: List<OwnerBadgesItem?>? = null,

	@field:SerializedName("shortViewCountText")
	val shortViewCountText: ShortViewCountText? = null,

	@field:SerializedName("channelThumbnailSupportedRenderers")
	val channelThumbnailSupportedRenderers: ChannelThumbnailSupportedRenderers? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class SectionsItem(

	@field:SerializedName("hotkeyDialogSectionRenderer")
	val hotkeyDialogSectionRenderer: HotkeyDialogSectionRenderer? = null
)

data class PromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Content(

	@field:SerializedName("verticalListRenderer")
	val verticalListRenderer: VerticalListRenderer? = null
)

data class ChipsItem(

	@field:SerializedName("chipCloudChipRenderer")
	val chipCloudChipRenderer: ChipCloudChipRenderer? = null
)

data class HotkeyAccessibilityLabel(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class CommandsItem(

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class Contents(

	@field:SerializedName("twoColumnSearchResultsRenderer")
	val twoColumnSearchResultsRenderer: TwoColumnSearchResultsRenderer? = null
)

data class ContinuationItemRenderer(

	@field:SerializedName("continuationEndpoint")
	val continuationEndpoint: ContinuationEndpoint? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null,

	@field:SerializedName("trigger")
	val trigger: String? = null
)

data class ThumbnailOverlayTimeStatusRenderer(

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null
)

data class ButtonRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("size")
	val size: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("isDisabled")
	val isDisabled: Boolean? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null,

	@field:SerializedName("command")
	val command: Command? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("iconPosition")
	val iconPosition: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class PreviousButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class WebResponseContextExtensionData(

	@field:SerializedName("hasDecorated")
	val hasDecorated: Boolean? = null
)

data class OriginalQuery(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class OwnerText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Logo(

	@field:SerializedName("topbarLogoRenderer")
	val topbarLogoRenderer: TopbarLogoRenderer? = null
)

data class SignalServiceEndpoint(

	@field:SerializedName("signal")
	val signal: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class MenuServiceItemRenderer(

	@field:SerializedName("hasSeparator")
	val hasSeparator: Boolean? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null
)

data class PromptMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SubMenu(

	@field:SerializedName("searchSubMenuRenderer")
	val searchSubMenuRenderer: SearchSubMenuRenderer? = null
)

data class ShortViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class MicrophoneButtonAriaLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ParamsItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class ChipBar(

	@field:SerializedName("chipCloudRenderer")
	val chipCloudRenderer: ChipCloudRenderer? = null
)

data class SearchEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("query")
	val query: String? = null,

	@field:SerializedName("params")
	val params: String? = null
)

data class CorrectedQueryEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class QoeLoggingContext(

	@field:SerializedName("serializedContextData")
	val serializedContextData: String? = null
)

data class VssLoggingContext(

	@field:SerializedName("serializedContextData")
	val serializedContextData: String? = null
)

data class PermissionsHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ThumbnailsItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class Text(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class ItemsItem(

	@field:SerializedName("menuServiceItemRenderer")
	val menuServiceItemRenderer: MenuServiceItemRenderer? = null,

	@field:SerializedName("menuNavigationItemRenderer")
	val menuNavigationItemRenderer: MenuNavigationItemRenderer? = null,

	@field:SerializedName("reelItemRenderer")
	val reelItemRenderer: ReelItemRenderer? = null,

	@field:SerializedName("videoRenderer")
	val videoRenderer: VideoRenderer? = null
)

data class LoggingDirectives(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("visibility")
	val visibility: Visibility? = null,

	@field:SerializedName("enableDisplayloggerExperiment")
	val enableDisplayloggerExperiment: Boolean? = null
)

data class VerticalListRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("collapsedItemCount")
	val collapsedItemCount: Int? = null,

	@field:SerializedName("collapsedStateButtonText")
	val collapsedStateButtonText: CollapsedStateButtonText? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class HotkeyDialogSectionRenderer(

	@field:SerializedName("options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class WatchEndpoint(

	@field:SerializedName("watchEndpointSupportedOnesieConfig")
	val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("playerParams")
	val playerParams: String? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("playerExtraUrlParams")
	val playerExtraUrlParams: List<PlayerExtraUrlParamsItem?>? = null,

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("continuePlayback")
	val continuePlayback: Boolean? = null,

	@field:SerializedName("loggingContext")
	val loggingContext: LoggingContext? = null
)

data class SectionListRenderer(

	@field:SerializedName("hideBottomSeparator")
	val hideBottomSeparator: Boolean? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("subMenu")
	val subMenu: SubMenu? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class GroupsItem(

	@field:SerializedName("searchFilterGroupRenderer")
	val searchFilterGroupRenderer: SearchFilterGroupRenderer? = null
)

data class MovingThumbnailDetails(

	@field:SerializedName("logAsMovingThumbnail")
	val logAsMovingThumbnail: Boolean? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class AddToPlaylistCommand(

	@field:SerializedName("onCreateListCommand")
	val onCreateListCommand: OnCreateListCommand? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("openMiniplayer")
	val openMiniplayer: Boolean? = null,

	@field:SerializedName("listType")
	val listType: String? = null,

	@field:SerializedName("videoIds")
	val videoIds: List<String?>? = null
)

data class LoggingContext(

	@field:SerializedName("vssLoggingContext")
	val vssLoggingContext: VssLoggingContext? = null,

	@field:SerializedName("qoeLoggingContext")
	val qoeLoggingContext: QoeLoggingContext? = null
)

data class MultiPageMenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
)

data class ThumbnailOverlayToggleButtonRenderer(

	@field:SerializedName("untoggledIcon")
	val untoggledIcon: UntoggledIcon? = null,

	@field:SerializedName("toggledIcon")
	val toggledIcon: ToggledIcon? = null,

	@field:SerializedName("toggledTooltip")
	val toggledTooltip: String? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("toggledAccessibility")
	val toggledAccessibility: ToggledAccessibility? = null,

	@field:SerializedName("untoggledTooltip")
	val untoggledTooltip: String? = null,

	@field:SerializedName("untoggledAccessibility")
	val untoggledAccessibility: UntoggledAccessibility? = null,

	@field:SerializedName("untoggledServiceEndpoint")
	val untoggledServiceEndpoint: UntoggledServiceEndpoint? = null,

	@field:SerializedName("toggledServiceEndpoint")
	val toggledServiceEndpoint: ToggledServiceEndpoint? = null,

	@field:SerializedName("isToggled")
	val isToggled: Boolean? = null
)

data class Overlay(

	@field:SerializedName("reelPlayerOverlayRenderer")
	val reelPlayerOverlayRenderer: ReelPlayerOverlayRenderer? = null
)

data class ConnectionErrorMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Searchbox(

	@field:SerializedName("fusionSearchboxRenderer")
	val fusionSearchboxRenderer: FusionSearchboxRenderer? = null
)

data class PlaceholderText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UnifiedSharePanelRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
)

data class ContinuationEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("continuationCommand")
	val continuationCommand: ContinuationCommand? = null
)

data class Label(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ExampleQuery1(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ReelWatchEndpoint(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("ustreamerConfig")
	val ustreamerConfig: String? = null,

	@field:SerializedName("overlay")
	val overlay: Overlay? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("playerParams")
	val playerParams: String? = null,

	@field:SerializedName("sequenceParams")
	val sequenceParams: String? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("sequenceProvider")
	val sequenceProvider: String? = null,

	@field:SerializedName("loggingContext")
	val loggingContext: LoggingContext? = null
)

data class SnippetText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShowingResultsForRenderer(

	@field:SerializedName("correctedQueryEndpoint")
	val correctedQueryEndpoint: CorrectedQueryEndpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showingResultsFor")
	val showingResultsFor: ShowingResultsFor? = null,

	@field:SerializedName("correctedQuery")
	val correctedQuery: CorrectedQuery? = null,

	@field:SerializedName("searchInsteadFor")
	val searchInsteadFor: SearchInsteadFor? = null,

	@field:SerializedName("originalQueryEndpoint")
	val originalQueryEndpoint: OriginalQueryEndpoint? = null,

	@field:SerializedName("originalQuery")
	val originalQuery: OriginalQuery? = null
)

data class CorrectedQuery(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class OptionsItem(

	@field:SerializedName("hotkeyDialogSectionOptionRenderer")
	val hotkeyDialogSectionOptionRenderer: HotkeyDialogSectionOptionRenderer? = null
)

data class Accessibility(

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class TopbarButtonsItem(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null,

	@field:SerializedName("topbarMenuButtonRenderer")
	val topbarMenuButtonRenderer: TopbarMenuButtonRenderer? = null
)

data class PlaylistEditEndpoint(

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class Title(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class Headline(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class BackButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ExitButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ChildVideoRenderer(

	@field:SerializedName("lengthText")
	val lengthText: LengthText? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class DisabledHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UserFeedbackEndpointProductSpecificValueData(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class DisabledSubtext(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class MovingThumbnailRenderer(

	@field:SerializedName("enableOverlay")
	val enableOverlay: Boolean? = null,

	@field:SerializedName("enableHoveredLogging")
	val enableHoveredLogging: Boolean? = null,

	@field:SerializedName("movingThumbnailDetails")
	val movingThumbnailDetails: MovingThumbnailDetails? = null
)

data class ViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class LoadingHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class BrowseEndpoint(

	@field:SerializedName("browseId")
	val browseId: String? = null,

	@field:SerializedName("canonicalBaseUrl")
	val canonicalBaseUrl: String? = null
)

data class ReelShelfRenderer(

	@field:SerializedName("button")
	val button: Button? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class VibrantColorPalette(

	@field:SerializedName("iconInactiveColor")
	val iconInactiveColor: Int? = null
)

data class VideoCountText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class UntoggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ClearButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class Menu(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null
)

data class SearchFilterGroupRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("filters")
	val filters: List<FiltersItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class RadioRenderer(

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("videoCountText")
	val videoCountText: VideoCountText? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("thumbnailText")
	val thumbnailText: ThumbnailText? = null,

	@field:SerializedName("videoCountShortText")
	val videoCountShortText: VideoCountShortText? = null,

	@field:SerializedName("videos")
	val videos: List<VideosItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("thumbnailOverlays")
	val thumbnailOverlays: List<ThumbnailOverlaysItem?>? = null,

	@field:SerializedName("longBylineText")
	val longBylineText: LongBylineText? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class DarkColorPalette(

	@field:SerializedName("iconInactiveColor")
	val iconInactiveColor: Int? = null,

	@field:SerializedName("iconDisabledColor")
	val iconDisabledColor: Int? = null,

	@field:SerializedName("section2Color")
	val section2Color: Int? = null
)

data class ShelfRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class SearchInsteadFor(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SnippetHoverText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class HotkeyDialogRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("dismissButton")
	val dismissButton: DismissButton? = null,

	@field:SerializedName("title")
	val title: Title? = null,

	@field:SerializedName("sections")
	val sections: List<SectionsItem?>? = null
)

data class OpenPopupAction(

	@field:SerializedName("popupType")
	val popupType: String? = null,

	@field:SerializedName("popup")
	val popup: Popup? = null,

	@field:SerializedName("beReused")
	val beReused: Boolean? = null
)

data class InlinePlaybackEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("watchEndpoint")
	val watchEndpoint: WatchEndpoint? = null
)

data class ConnectionErrorHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class CommandMetadata(

	@field:SerializedName("webCommandMetadata")
	val webCommandMetadata: WebCommandMetadata? = null
)

data class Config(

	@field:SerializedName("webSearchboxConfig")
	val webSearchboxConfig: WebSearchboxConfig? = null
)

data class UserFeedbackEndpoint(

	@field:SerializedName("additionalDatas")
	val additionalDatas: List<AdditionalDatasItem?>? = null
)

data class TopbarLogoRenderer(

	@field:SerializedName("endpoint")
	val endpoint: Endpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("iconImage")
	val iconImage: IconImage? = null,

	@field:SerializedName("tooltipText")
	val tooltipText: TooltipText? = null,

	@field:SerializedName("overrideEntityKey")
	val overrideEntityKey: String? = null
)

data class SearchFilterOptionsDialogRenderer(

	@field:SerializedName("groups")
	val groups: List<GroupsItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class Html5PlaybackOnesieConfig(

	@field:SerializedName("commonConfig")
	val commonConfig: CommonConfig? = null
)

data class TwoColumnSearchResultsRenderer(

	@field:SerializedName("primaryContents")
	val primaryContents: PrimaryContents? = null
)

data class ActionsItem(

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("addToPlaylistCommand")
	val addToPlaylistCommand: AddToPlaylistCommand? = null,

	@field:SerializedName("addedVideoId")
	val addedVideoId: String? = null,

	@field:SerializedName("action")
	val action: String? = null,

	@field:SerializedName("removedVideoId")
	val removedVideoId: String? = null,

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("signalAction")
	val signalAction: SignalAction? = null
)

data class MainAppWebResponseContext(

	@field:SerializedName("trackingParam")
	val trackingParam: String? = null,

	@field:SerializedName("loggedOut")
	val loggedOut: Boolean? = null
)

data class ItemSectionRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class AccessibilityData(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("label")
	val label: String? = null
)

data class SearchFilterButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class ThumbnailOverlayBottomPanelRenderer(

	@field:SerializedName("icon")
	val icon: Icon? = null
)

data class SearchFilterRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DismissButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class WebCommandMetadata(

	@field:SerializedName("rootVe")
	val rootVe: Int? = null,

	@field:SerializedName("webPageType")
	val webPageType: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("apiUrl")
	val apiUrl: String? = null,

	@field:SerializedName("sendPost")
	val sendPost: Boolean? = null,

	@field:SerializedName("ignoreNavigation")
	val ignoreNavigation: Boolean? = null
)

data class Header(

	@field:SerializedName("searchHeaderRenderer")
	val searchHeaderRenderer: SearchHeaderRenderer? = null
)

data class WatchEndpointSupportedOnesieConfig(

	@field:SerializedName("html5PlaybackOnesieConfig")
	val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig? = null
)

data class MenuRequest(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null
)

data class CommonConfig(

	@field:SerializedName("url")
	val url: String? = null
)

data class DetailedMetadataSnippetsItem(

	@field:SerializedName("maxOneLine")
	val maxOneLine: Boolean? = null,

	@field:SerializedName("snippetText")
	val snippetText: SnippetText? = null,

	@field:SerializedName("snippetHoverText")
	val snippetHoverText: SnippetHoverText? = null
)

data class OwnerBadgesItem(

	@field:SerializedName("metadataBadgeRenderer")
	val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

data class RichThumbnail(

	@field:SerializedName("movingThumbnailRenderer")
	val movingThumbnailRenderer: MovingThumbnailRenderer? = null
)

data class VideosItem(

	@field:SerializedName("childVideoRenderer")
	val childVideoRenderer: ChildVideoRenderer? = null
)

data class Topbar(

	@field:SerializedName("desktopTopbarRenderer")
	val desktopTopbarRenderer: DesktopTopbarRenderer? = null
)

data class BadgesItem(

	@field:SerializedName("metadataBadgeRenderer")
	val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

data class HotkeyDialogSectionOptionRenderer(

	@field:SerializedName("hotkey")
	val hotkey: String? = null,

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("hotkeyAccessibilityLabel")
	val hotkeyAccessibilityLabel: HotkeyAccessibilityLabel? = null
)

data class LengthText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class Visibility(

	@field:SerializedName("types")
	val types: String? = null
)

data class VoiceSearchDialogRenderer(

	@field:SerializedName("microphoneButtonAriaLabel")
	val microphoneButtonAriaLabel: MicrophoneButtonAriaLabel? = null,

	@field:SerializedName("promptMicrophoneLabel")
	val promptMicrophoneLabel: PromptMicrophoneLabel? = null,

	@field:SerializedName("promptHeader")
	val promptHeader: PromptHeader? = null,

	@field:SerializedName("connectionErrorMicrophoneLabel")
	val connectionErrorMicrophoneLabel: ConnectionErrorMicrophoneLabel? = null,

	@field:SerializedName("disabledSubtext")
	val disabledSubtext: DisabledSubtext? = null,

	@field:SerializedName("permissionsHeader")
	val permissionsHeader: PermissionsHeader? = null,

	@field:SerializedName("exampleQuery1")
	val exampleQuery1: ExampleQuery1? = null,

	@field:SerializedName("exitButton")
	val exitButton: ExitButton? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("microphoneOffPromptHeader")
	val microphoneOffPromptHeader: MicrophoneOffPromptHeader? = null,

	@field:SerializedName("exampleQuery2")
	val exampleQuery2: ExampleQuery2? = null,

	@field:SerializedName("connectionErrorHeader")
	val connectionErrorHeader: ConnectionErrorHeader? = null,

	@field:SerializedName("placeholderHeader")
	val placeholderHeader: PlaceholderHeader? = null,

	@field:SerializedName("disabledHeader")
	val disabledHeader: DisabledHeader? = null,

	@field:SerializedName("loadingHeader")
	val loadingHeader: LoadingHeader? = null,

	@field:SerializedName("permissionsSubtext")
	val permissionsSubtext: PermissionsSubtext? = null
)

data class HotkeyDialog(

	@field:SerializedName("hotkeyDialogRenderer")
	val hotkeyDialogRenderer: HotkeyDialogRenderer? = null
)

data class SignalAction(

	@field:SerializedName("signal")
	val signal: String? = null
)

data class PermissionsSubtext(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ThumbnailText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class WebSearchboxConfig(

	@field:SerializedName("hasOnscreenKeyboard")
	val hasOnscreenKeyboard: Boolean? = null,

	@field:SerializedName("requestLanguage")
	val requestLanguage: String? = null,

	@field:SerializedName("requestDomain")
	val requestDomain: String? = null,

	@field:SerializedName("focusSearchbox")
	val focusSearchbox: Boolean? = null
)

data class ShowReloadUiCommand(

	@field:SerializedName("targetId")
	val targetId: String? = null
)

data class IconImage(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ForwardButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class FiltersItem(

	@field:SerializedName("searchFilterRenderer")
	val searchFilterRenderer: SearchFilterRenderer? = null
)

data class ThumbnailOverlayHoverTextRenderer(

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null
)

data class ShortBylineText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ChipCloudRenderer(

	@field:SerializedName("nextButton")
	val nextButton: NextButton? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("previousButton")
	val previousButton: PreviousButton? = null,

	@field:SerializedName("chips")
	val chips: List<ChipsItem?>? = null,

	@field:SerializedName("loggingDirectives")
	val loggingDirectives: LoggingDirectives? = null
)

data class Thumbnail(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null,

	@field:SerializedName("vibrantColorPalette")
	val vibrantColorPalette: VibrantColorPalette? = null,

	@field:SerializedName("sampledThumbnailColor")
	val sampledThumbnailColor: SampledThumbnailColor? = null,

	@field:SerializedName("darkColorPalette")
	val darkColorPalette: DarkColorPalette? = null,

	@field:SerializedName("isOriginalAspectRatio")
	val isOriginalAspectRatio: Boolean? = null
)

data class MetadataBadgeRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null
)

data class Button(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null
)

data class Popup(

	@field:SerializedName("searchFilterOptionsDialogRenderer")
	val searchFilterOptionsDialogRenderer: SearchFilterOptionsDialogRenderer? = null,

	@field:SerializedName("unifiedSharePanelRenderer")
	val unifiedSharePanelRenderer: UnifiedSharePanelRenderer? = null,

	@field:SerializedName("multiPageMenuRenderer")
	val multiPageMenuRenderer: MultiPageMenuRenderer? = null,

	@field:SerializedName("voiceSearchDialogRenderer")
	val voiceSearchDialogRenderer: VoiceSearchDialogRenderer? = null
)

data class MenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class ToggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)
