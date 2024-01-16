package com.example.hdvideoplayer_youtube.ModelData

import com.google.gson.annotations.SerializedName

data class TrendingListModel(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: Contents? = null,

	@field:SerializedName("responseContext")
	val responseContext: ResponseContext? = null,

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("topbar")
	val topbar: Topbar? = null
)

data class ExitButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class CommandsItem(

	@field:SerializedName("openPopupAction")
	val openPopupAction: OpenPopupAction? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
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

data class HotkeyDialogSectionOptionRenderer(

	@field:SerializedName("hotkey")
	val hotkey: String? = null,

	@field:SerializedName("label")
	val label: Label? = null,

	@field:SerializedName("hotkeyAccessibilityLabel")
	val hotkeyAccessibilityLabel: HotkeyAccessibilityLabel? = null
)

data class SectionsItem(

	@field:SerializedName("hotkeyDialogSectionRenderer")
	val hotkeyDialogSectionRenderer: HotkeyDialogSectionRenderer? = null
)

data class SignInEndpoint(

	@field:SerializedName("idamTag")
	val idamTag: String? = null
)

data class ShareEntityServiceEndpoint(

	@field:SerializedName("serializedShareEntity")
	val serializedShareEntity: String? = null,

	@field:SerializedName("commands")
	val commands: List<CommandsItem?>? = null
)

data class Topbar(

	@field:SerializedName("desktopTopbarRenderer")
	val desktopTopbarRenderer: DesktopTopbarRenderer? = null
)

data class ThumbnailOverlayNowPlayingRenderer(

	@field:SerializedName("text")
	val text: Text? = null
)

data class IconImage(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class ExpandedShelfContentsRenderer(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class OwnerBadgesItem(

	@field:SerializedName("metadataBadgeRenderer")
	val metadataBadgeRenderer: MetadataBadgeRenderer? = null
)

data class LengthText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class OptionsItem(

	@field:SerializedName("hotkeyDialogSectionOptionRenderer")
	val hotkeyDialogSectionOptionRenderer: HotkeyDialogSectionOptionRenderer? = null
)

data class TooltipText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Thumbnail(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
)

data class TopbarButtonsItem(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null,

	@field:SerializedName("topbarMenuButtonRenderer")
	val topbarMenuButtonRenderer: TopbarMenuButtonRenderer? = null
)

data class MicrophoneButtonAriaLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class MultiPageMenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
)

data class CreatePlaylistServiceEndpoint(

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("videoIds")
	val videoIds: List<String?>? = null
)

data class ItemsItem(

	@field:SerializedName("videoRenderer")
	val videoRenderer: VideoRenderer? = null,

	@field:SerializedName("menuServiceItemRenderer")
	val menuServiceItemRenderer: MenuServiceItemRenderer? = null
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

data class Title(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class PermissionsHeader(

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

data class Text(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null,

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class Searchbox(

	@field:SerializedName("fusionSearchboxRenderer")
	val fusionSearchboxRenderer: FusionSearchboxRenderer? = null
)

data class HotkeyDialogSectionRenderer(

	@field:SerializedName("options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("title")
	val title: Title? = null
)

data class Config(

	@field:SerializedName("webSearchboxConfig")
	val webSearchboxConfig: WebSearchboxConfig? = null
)

data class SearchEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("searchEndpoint")
	val searchEndpoint: SearchEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("query")
	val query: String? = null
)

data class DisabledSubtext(

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

data class PromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class WatchEndpointSupportedOnesieConfig(

	@field:SerializedName("html5PlaybackOnesieConfig")
	val html5PlaybackOnesieConfig: Html5PlaybackOnesieConfig? = null
)

data class ThumbnailOverlaysItem(

	@field:SerializedName("thumbnailOverlayNowPlayingRenderer")
	val thumbnailOverlayNowPlayingRenderer: ThumbnailOverlayNowPlayingRenderer? = null,

	@field:SerializedName("thumbnailOverlayToggleButtonRenderer")
	val thumbnailOverlayToggleButtonRenderer: ThumbnailOverlayToggleButtonRenderer? = null,

	@field:SerializedName("thumbnailOverlayTimeStatusRenderer")
	val thumbnailOverlayTimeStatusRenderer: ThumbnailOverlayTimeStatusRenderer? = null
)

data class Header(

	@field:SerializedName("c4TabbedHeaderRenderer")
	val c4TabbedHeaderRenderer: C4TabbedHeaderRenderer? = null
)

data class ExampleQuery1(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Menu(

	@field:SerializedName("menuRenderer")
	val menuRenderer: MenuRenderer? = null
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

data class WebResponseContextExtensionData(

	@field:SerializedName("hasDecorated")
	val hasDecorated: Boolean? = null
)

data class ContentsItem(

	@field:SerializedName("itemSectionRenderer")
	val itemSectionRenderer: ItemSectionRenderer? = null,

	@field:SerializedName("shelfRenderer")
	val shelfRenderer: ShelfRenderer? = null
)

data class MainAppWebResponseContext(

	@field:SerializedName("trackingParam")
	val trackingParam: String? = null,

	@field:SerializedName("loggedOut")
	val loggedOut: Boolean? = null
)

data class ToggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class ViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class BackButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class MenuRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class ClearButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class TabRenderer(

	@field:SerializedName("endpoint")
	val endpoint: Endpoint? = null,

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("tabIdentifier")
	val tabIdentifier: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("selected")
	val selected: Boolean? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class Contents(

	@field:SerializedName("twoColumnBrowseResultsRenderer")
	val twoColumnBrowseResultsRenderer: TwoColumnBrowseResultsRenderer? = null
)

data class ShortViewCountText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null
)

data class DescriptionSnippet(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class A11ySkipNavigationButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
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

data class MovingThumbnailDetails(

	@field:SerializedName("logAsMovingThumbnail")
	val logAsMovingThumbnail: Boolean? = null,

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
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

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class OpenPopupAction(

	@field:SerializedName("popupType")
	val popupType: String? = null,

	@field:SerializedName("popup")
	val popup: Popup? = null,

	@field:SerializedName("beReused")
	val beReused: Boolean? = null
)

data class Icon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class UntoggledAccessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class SectionListRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("targetId")
	val targetId: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class UnifiedSharePanelRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showLoadingSpinner")
	val showLoadingSpinner: Boolean? = null
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

data class VoiceSearchButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class OnCreateListCommand(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("createPlaylistServiceEndpoint")
	val createPlaylistServiceEndpoint: CreatePlaylistServiceEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class ExampleQuery2(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class SignalServiceEndpoint(

	@field:SerializedName("signal")
	val signal: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
)

data class MenuServiceItemRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("text")
	val text: Text? = null,

	@field:SerializedName("serviceEndpoint")
	val serviceEndpoint: ServiceEndpoint? = null
)

data class CommandMetadata(

	@field:SerializedName("webCommandMetadata")
	val webCommandMetadata: WebCommandMetadata? = null
)

data class ShortBylineText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ServiceTrackingParamsItem(

	@field:SerializedName("service")
	val service: String? = null,

	@field:SerializedName("params")
	val params: List<ParamsItem?>? = null
)

data class Command(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("signalServiceEndpoint")
	val signalServiceEndpoint: SignalServiceEndpoint? = null
)

data class Content(

	@field:SerializedName("sectionListRenderer")
	val sectionListRenderer: SectionListRenderer? = null,

	@field:SerializedName("expandedShelfContentsRenderer")
	val expandedShelfContentsRenderer: ExpandedShelfContentsRenderer? = null
)

data class PlaceholderHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ConnectionErrorHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class WatchEndpoint(

	@field:SerializedName("watchEndpointSupportedOnesieConfig")
	val watchEndpointSupportedOnesieConfig: WatchEndpointSupportedOnesieConfig? = null,

	@field:SerializedName("videoId")
	val videoId: String? = null
)

data class ParamsItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)

data class Avatar(

	@field:SerializedName("thumbnails")
	val thumbnails: List<ThumbnailsItem?>? = null
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

data class TwoColumnBrowseResultsRenderer(

	@field:SerializedName("tabs")
	val tabs: List<TabsItem?>? = null
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

data class VideoRenderer(

	@field:SerializedName("lengthText")
	val lengthText: LengthText? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

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

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("showActionMenu")
	val showActionMenu: Boolean? = null,

	@field:SerializedName("publishedTimeText")
	val publishedTimeText: PublishedTimeText? = null,

	@field:SerializedName("viewCountText")
	val viewCountText: ViewCountText? = null,

	@field:SerializedName("shortViewCountText")
	val shortViewCountText: ShortViewCountText? = null,

	@field:SerializedName("channelThumbnailSupportedRenderers")
	val channelThumbnailSupportedRenderers: ChannelThumbnailSupportedRenderers? = null,

	@field:SerializedName("descriptionSnippet")
	val descriptionSnippet: DescriptionSnippet? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null,

	@field:SerializedName("ownerBadges")
	val ownerBadges: List<OwnerBadgesItem?>? = null
)

data class PermissionsSubtext(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class BrowseEndpoint(

	@field:SerializedName("browseId")
	val browseId: String? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("canonicalBaseUrl")
	val canonicalBaseUrl: String? = null
)

data class MicrophoneOffPromptHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class DismissButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
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

data class SignalAction(

	@field:SerializedName("signal")
	val signal: String? = null
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

	@field:SerializedName("signInEndpoint")
	val signInEndpoint: SignInEndpoint? = null
)

data class Html5PlaybackOnesieConfig(

	@field:SerializedName("commonConfig")
	val commonConfig: CommonConfig? = null
)

data class LoadingHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class PlaylistEditEndpoint(

	@field:SerializedName("playlistId")
	val playlistId: String? = null,

	@field:SerializedName("actions")
	val actions: List<ActionsItem?>? = null
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

data class ChannelThumbnailWithLinkRenderer(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null,

	@field:SerializedName("accessibility")
	val accessibility: Accessibility? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class ChannelThumbnailSupportedRenderers(

	@field:SerializedName("channelThumbnailWithLinkRenderer")
	val channelThumbnailWithLinkRenderer: ChannelThumbnailWithLinkRenderer? = null
)

data class PublishedTimeText(

	@field:SerializedName("simpleText")
	val simpleText: String? = null
)

data class ToggledServiceEndpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("playlistEditEndpoint")
	val playlistEditEndpoint: PlaylistEditEndpoint? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null
)

data class OwnerText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ResponseContext(

	@field:SerializedName("serviceTrackingParams")
	val serviceTrackingParams: List<ServiceTrackingParamsItem?>? = null,

	@field:SerializedName("webResponseContextExtensionData")
	val webResponseContextExtensionData: WebResponseContextExtensionData? = null,

	@field:SerializedName("maxAgeSeconds")
	val maxAgeSeconds: Int? = null,

	@field:SerializedName("visitorData")
	val visitorData: String? = null,

	@field:SerializedName("mainAppWebResponseContext")
	val mainAppWebResponseContext: MainAppWebResponseContext? = null
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
	val sendPost: Boolean? = null
)

data class UntoggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class C4TabbedHeaderRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("avatar")
	val avatar: Avatar? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class LongBylineText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class ShelfRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("content")
	val content: Content? = null
)

data class Label(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Endpoint(

	@field:SerializedName("commandMetadata")
	val commandMetadata: CommandMetadata? = null,

	@field:SerializedName("clickTrackingParams")
	val clickTrackingParams: String? = null,

	@field:SerializedName("browseEndpoint")
	val browseEndpoint: BrowseEndpoint? = null
)

data class ThumbnailsItem(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
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

data class ConnectionErrorMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Accessibility(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class ThumbnailOverlayTimeStatusRenderer(

	@field:SerializedName("style")
	val style: String? = null,

	@field:SerializedName("text")
	val text: Text? = null
)

data class ToggledIcon(

	@field:SerializedName("iconType")
	val iconType: String? = null
)

data class PromptMicrophoneLabel(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class Logo(

	@field:SerializedName("topbarLogoRenderer")
	val topbarLogoRenderer: TopbarLogoRenderer? = null
)

data class PlaceholderText(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class TabsItem(

	@field:SerializedName("tabRenderer")
	val tabRenderer: TabRenderer? = null
)

data class MetadataBadgeRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("icon")
	val icon: Icon? = null,

	@field:SerializedName("tooltip")
	val tooltip: String? = null,

	@field:SerializedName("style")
	val style: String? = null
)

data class RunsItem(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("navigationEndpoint")
	val navigationEndpoint: NavigationEndpoint? = null
)

data class ItemSectionRenderer(

	@field:SerializedName("trackingParams")
	val trackingParams: String? = null,

	@field:SerializedName("contents")
	val contents: List<ContentsItem?>? = null
)

data class HotkeyAccessibilityLabel(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null
)

data class ForwardButton(

	@field:SerializedName("buttonRenderer")
	val buttonRenderer: ButtonRenderer? = null
)

data class DisabledHeader(

	@field:SerializedName("runs")
	val runs: List<RunsItem?>? = null
)

data class HotkeyDialog(

	@field:SerializedName("hotkeyDialogRenderer")
	val hotkeyDialogRenderer: HotkeyDialogRenderer? = null
)

data class Popup(

	@field:SerializedName("unifiedSharePanelRenderer")
	val unifiedSharePanelRenderer: UnifiedSharePanelRenderer? = null,

	@field:SerializedName("multiPageMenuRenderer")
	val multiPageMenuRenderer: MultiPageMenuRenderer? = null,

	@field:SerializedName("voiceSearchDialogRenderer")
	val voiceSearchDialogRenderer: VoiceSearchDialogRenderer? = null
)

data class AccessibilityData(

	@field:SerializedName("accessibilityData")
	val accessibilityData: AccessibilityData? = null,

	@field:SerializedName("label")
	val label: String? = null
)

data class RichThumbnail(

	@field:SerializedName("movingThumbnailRenderer")
	val movingThumbnailRenderer: MovingThumbnailRenderer? = null
)
